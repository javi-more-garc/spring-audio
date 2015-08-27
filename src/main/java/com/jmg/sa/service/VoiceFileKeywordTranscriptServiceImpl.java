/**
 * 
 */
package com.jmg.sa.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmg.sa.domain.VoiceFileKeywordTrascript;
import com.jmg.sa.repository.VoiceFileKeywordTrascriptRepository;
import com.jmg.sa.util.SecurityUtils;

/**
 * @author Javier Moreno Garcia
 *
 */
@Service
@Transactional(readOnly = true)
public class VoiceFileKeywordTranscriptServiceImpl implements VoiceFileKeywordTranscriptService{

    @Inject
    private VoiceFileKeywordTrascriptRepository repository;
    
    @Override
    public List<VoiceFileKeywordTrascript> listKeywordsForLoggedUser(Long voiceFileId) {

        // get logged user's email
        String loggedUserEmail = SecurityUtils.getLoggedUserEmail();

        // find using repository
        List<VoiceFileKeywordTrascript> result = repository.findByVoiceFile_idAndUser_email(voiceFileId, loggedUserEmail);

        return result;

    }

    @Override
    public Page<VoiceFileKeywordTrascript> listKeywordsForLoggedUser(Long voiceFileId, Pageable pageable) {
        
        // get logged user's email
        String loggedUserEmail = SecurityUtils.getLoggedUserEmail();

        return repository.findByVoiceFile_idAndUser_email(voiceFileId, loggedUserEmail, pageable);
    }
    
    @Override
    public List<VoiceFileKeywordTrascript> searchKeywordsForLoggedUser(Long voiceFileId, String term) {
        
        // get logged user's email
        String loggedUserEmail = SecurityUtils.getLoggedUserEmail();        

        // TODO use all terms
        return repository.findByVoiceFile_idAndUser_emailAndName(voiceFileId, loggedUserEmail, term);
    }
}
