/**
 * 
 */
package com.jmg.sa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jmg.sa.domain.VoiceFileKeyword;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileKeywordService {

    Page<VoiceFileKeyword> listFiles(Long voiceFileId, Pageable pageable);

}
