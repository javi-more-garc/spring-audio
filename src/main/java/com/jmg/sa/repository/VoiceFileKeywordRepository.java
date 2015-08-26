/**
 * 
 */
package com.jmg.sa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jmg.sa.domain.VoiceFileKeyword;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileKeywordRepository extends JpaRepository<VoiceFileKeyword, Long>{
    
    List<VoiceFileKeyword> findByVoiceFile_idAndUser_email(Long voiceFileId, String loggedUserEmail);

    Page<VoiceFileKeyword> findByVoiceFile_idAndUser_email(Long id, String email, Pageable pageable);

}
