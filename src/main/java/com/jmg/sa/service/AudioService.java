/**
 * 
 */
package com.jmg.sa.service;

import java.io.File;

import com.jmg.sa.bean.GenericResponse;
import com.jmg.sa.bean.ListFolderResponse;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface AudioService {

    GenericResponse addNewFileAsync(Long externalId, File file);

    ListFolderResponse listFiles();

}
