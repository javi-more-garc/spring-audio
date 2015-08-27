/**
 * 
 */
package com.jmg.sa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jmg.sa.domain.VoiceFileKeywordAnalytics;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileKeywordAnalyticsService {
    
    List<VoiceFileKeywordAnalytics> listKeywordsForLoggedUser(Long voiceFileId);

    Page<VoiceFileKeywordAnalytics> listKeywordsForLoggedUser(Long voiceFileId, Pageable pageable);

    List<VoiceFileKeywordAnalytics> searchKeywordsForLoggedUser(Long voiceFileId, String term);

}
