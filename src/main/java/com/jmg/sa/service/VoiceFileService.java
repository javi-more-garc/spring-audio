/**
 * 
 */
package com.jmg.sa.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.jmg.sa.bean.GetFileAnalyticsResponse;
import com.jmg.sa.bean.GetFileKeywordsResponse;
import com.jmg.sa.bean.FileUploadResponse;
import com.jmg.sa.domain.VoiceFile;
import com.jmg.sa.domain.VoiceFileStatus;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceFileService {

    List<VoiceFile> listFiles(VoiceFileStatus status);

    List<VoiceFile> listFilesForLoggedUser();

    Page<VoiceFile> listFilesForLoggedUser(Pageable pageable);

    VoiceFile findOneForLoggedUser(Long id);

    void addNewFileForLoggedUser(MultipartFile file) throws IOException;
    
    void update(Long id, FileUploadResponse response);
    
    void update(Long id, GetFileKeywordsResponse trascript, GetFileAnalyticsResponse analytics);

    void updateStatus(Long id, VoiceFileStatus status);

}
