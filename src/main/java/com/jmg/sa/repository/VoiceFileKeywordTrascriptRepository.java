/**
 * 
 */
package com.jmg.sa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jmg.sa.domain.VoiceFileKeywordTrascript;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileKeywordTrascriptRepository extends JpaRepository<VoiceFileKeywordTrascript, Long>{
    
    List<VoiceFileKeywordTrascript> findByVoiceFile_idAndUser_email(Long voiceFileId, String loggedUserEmail);

    Page<VoiceFileKeywordTrascript> findByVoiceFile_idAndUser_email(Long id, String email, Pageable pageable);
    
    List<VoiceFileKeywordTrascript> findByVoiceFile_idAndUser_emailAndName(Long voiceFileId, String loggedUserEmail, String name);


}
