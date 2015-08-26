/**
 * 
 */
package com.jmg.sa.service;

import java.io.File;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jmg.sa.domain.VoiceFile;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileService {

    void addNewFile(File file);

    Page<VoiceFile> listFiles(Pageable pageable);

}
