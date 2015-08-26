/**
 * 
 */
package com.jmg.sa.service;

import java.io.File;
import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jmg.sa.domain.VoiceFile;
import com.jmg.sa.domain.VoiceFileContent;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileService {

    Page<VoiceFile> listFiles(Pageable pageable);
    
    VoiceFile findOne(Long id);

    void addNewFile(File file) throws IOException;

    VoiceFileContent getContent(Long id);

}
