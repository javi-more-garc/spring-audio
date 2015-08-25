/**
 * 
 */
package com.jmg.sa.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Moreno Garcia
 *
 */
public class ListFolderResponse extends GenericResponse{
    
    private List<FolderContent> folderContents = new ArrayList<>();

    public List<FolderContent> getFolderContents() {
        return this.folderContents;
    }

    public void setFolderContents(List<FolderContent> folderContents) {
        this.folderContents = folderContents;
    }

}
