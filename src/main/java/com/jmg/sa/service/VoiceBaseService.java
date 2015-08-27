/**
 * 
 */
package com.jmg.sa.service;

import java.io.IOException;

import com.jmg.sa.bean.FileUploadResponse;
import com.jmg.sa.bean.GetFileAnalyticsResponse;
import com.jmg.sa.bean.GetFileKeywordsResponse;
import com.jmg.sa.bean.ListFolderResponse;
import com.jmg.sa.domain.VoiceFileContent;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface VoiceBaseService {

    FileUploadResponse addNewFile(VoiceFileContent content) throws IOException;

    ListFolderResponse listFiles();
    
    boolean isProcessed(String mediaId);

    GetFileKeywordsResponse getKeywordsContent(String mediaId);

    GetFileAnalyticsResponse getAnalyticsContent(String mediaId);

    boolean delete(String mediaId);


}
