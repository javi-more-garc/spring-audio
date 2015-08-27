/**
 * 
 */
package com.jmg.sa.service;

import com.jmg.sa.domain.VoiceFileContent;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileContentService {
    
    VoiceFileContent getContent(Long voiceFileId);

    VoiceFileContent getContentForLoggedUser(Long voiceFileId);
}
