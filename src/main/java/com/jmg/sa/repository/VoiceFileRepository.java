/**
 * 
 */
package com.jmg.sa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jmg.sa.domain.VoiceFile;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileRepository extends JpaRepository<VoiceFile, Long>{
    
    List<VoiceFile> findByUser_email(String loggedUserEmail);

    Page<VoiceFile> findByUser_email(String email, Pageable pageable);

}
