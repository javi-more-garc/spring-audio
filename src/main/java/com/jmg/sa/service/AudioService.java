/**
 * 
 */
package com.jmg.sa.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.jmg.sa.bean.GenericResponse;
import com.jmg.sa.bean.ListFolderResponse;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface AudioService {

    GenericResponse addNewFileAsync(Long externalId, MultipartFile file) throws IOException;

    ListFolderResponse listFiles();


}
