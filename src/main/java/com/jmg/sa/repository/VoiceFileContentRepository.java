/**
 * 
 */
package com.jmg.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmg.sa.domain.VoiceFileContent;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileContentRepository extends JpaRepository<VoiceFileContent, Long>{
    
    VoiceFileContent findByVoiceFile_id(Long id);
    

}
