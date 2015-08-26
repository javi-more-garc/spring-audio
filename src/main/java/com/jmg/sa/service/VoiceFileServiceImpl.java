/**
 * 
 */
package com.jmg.sa.service;

import java.io.File;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmg.sa.domain.User;
import com.jmg.sa.domain.VoiceFile;
import com.jmg.sa.repository.VoiceFileRepository;
import com.jmg.sa.util.SecurityUtils;

/**
 * @author Javier Moreno Garcia
 *
 */
@Service
@Transactional(readOnly = true)
public class VoiceFileServiceImpl implements VoiceFileService {

    @Inject
    private AudioService audioService;
    
    @Inject
    private VoiceFileRepository voiceFileRepository;

    @Inject
    private EntityFinderSupport efSupport;

    // TODO - Introduce callback mapping that will be called by voicebase when a file is processed

    @Override
    @Transactional(readOnly = false)
    public void addNewFile(File file) {

        // get logged user
        User loggedUser = efSupport.checkLoggedUserExistAndReturn();
        
        // create new entity
        VoiceFile voiceFile = new VoiceFile(file.getName(), loggedUser);

        // save file
        voiceFileRepository.save(voiceFile);
        
        //
        // call external API (async)
        
        audioService.addNewFileAsync(voiceFile.getId(), file);       

    }

    @Override
    public Page<VoiceFile> listFiles(Pageable pageable) {

        // get logged user's email
        String loggedUserEmail = SecurityUtils.getLoggedUserEmail();

        // find using repository
        Page<VoiceFile> result = voiceFileRepository.findByUser_email(loggedUserEmail, pageable);
       

        return result;

    }

}
