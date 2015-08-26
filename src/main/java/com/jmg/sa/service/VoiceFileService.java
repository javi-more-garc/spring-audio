/**
 * 
 */
package com.jmg.sa.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.jmg.sa.domain.VoiceFile;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileService {

    Page<VoiceFile> listFiles(Pageable pageable);
    
    VoiceFile findOne(Long id);

    void addNewFile(MultipartFile file) throws IOException;

}
