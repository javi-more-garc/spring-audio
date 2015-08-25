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

    GenericResponse addNewFile(File file);

    ListFolderResponse listFiles();

}
