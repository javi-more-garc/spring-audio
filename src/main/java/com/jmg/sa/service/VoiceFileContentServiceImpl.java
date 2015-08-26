/**
 * 
 */
package com.jmg.sa.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmg.sa.domain.VoiceFileContent;

/**
 * @author Javier Moreno Garcia
 *
 */
@Service
@Transactional(readOnly = true)
public class VoiceFileContentServiceImpl implements VoiceFileContentService{

    @Inject
    private EntityFinderSupport efSupport;

    @Override
    public VoiceFileContent getContent(Long voiceFileId) {

        // get voice file content
        VoiceFileContent content = efSupport.checkVoiceFileContentExistAndReturn(voiceFileId);

        // return it
        return content;
    }
}
