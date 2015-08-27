/**
 * 
 */
package com.jmg.sa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jmg.sa.domain.VoiceFileKeywordTrascript;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileKeywordTranscriptService {
    
    List<VoiceFileKeywordTrascript> listKeywordsForLoggedUser(Long voiceFileId);

    Page<VoiceFileKeywordTrascript> listKeywordsForLoggedUser(Long voiceFileId, Pageable pageable);

    List<VoiceFileKeywordTrascript> searchKeywordsForLoggedUser(Long voiceFileId, String term);

}
