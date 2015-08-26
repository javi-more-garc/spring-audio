/**
 * 
 */
package com.jmg.sa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jmg.sa.domain.VoiceFileKeyword;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileKeywordService {
    
    List<VoiceFileKeyword> listKeywords(Long voiceFileId);

    Page<VoiceFileKeyword> listKeywords(Long voiceFileId, Pageable pageable);

}
