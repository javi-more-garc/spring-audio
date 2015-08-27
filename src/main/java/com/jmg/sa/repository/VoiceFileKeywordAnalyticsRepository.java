/**
 * 
 */
package com.jmg.sa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jmg.sa.domain.VoiceFileKeywordAnalytics;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileKeywordAnalyticsRepository extends JpaRepository<VoiceFileKeywordAnalytics, Long>{
    
    List<VoiceFileKeywordAnalytics> findByVoiceFile_idAndUser_email(Long voiceFileId, String loggedUserEmail);

    Page<VoiceFileKeywordAnalytics> findByVoiceFile_idAndUser_email(Long id, String email, Pageable pageable);
    
    List<VoiceFileKeywordAnalytics> findByVoiceFile_idAndUser_emailAndName(Long voiceFileId, String loggedUserEmail, String name);


}
