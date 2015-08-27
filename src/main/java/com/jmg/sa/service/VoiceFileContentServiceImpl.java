/**
 * 
 */
package com.jmg.sa.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmg.sa.domain.VoiceFileContent;
import com.jmg.sa.repository.VoiceFileContentRepository;

/**
 * @author Javier Moreno Garcia
 *
 */
@Service
@Transactional(readOnly = true)
public class VoiceFileContentServiceImpl implements VoiceFileContentService {

    @Inject
    private EntityFinderSupport efSupport;

    @Inject
    private VoiceFileContentRepository voiceFileContentRepository;

    @Override
    public VoiceFileContent getContent(Long voiceFileId) {

        // get voice file content
        VoiceFileContent content = voiceFileContentRepository.findByVoiceFile_id(voiceFileId);

        // return it
        return content;
    }

    @Override
    public VoiceFileContent getContentForLoggedUser(Long voiceFileId) {

        // get voice file content
        VoiceFileContent content = efSupport.checkVoiceFileContentExistAndReturn(voiceFileId);

        // return it
        return content;
    }

}
