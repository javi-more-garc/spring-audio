/**
 * 
 */
package com.jmg.sa.bean;

/**
 * @author Javier Moreno Garcia
 *
 */
public class GetFileStatusResponse extends GenericResponse {

    private String fileStatus;

    public String getFileStatus() {
        return this.fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }
    
    public boolean isProcessed(){
        return "MACHINECOMPLETE".equals(fileStatus) || "HUMANCOMPLETE".equals(fileStatus);
    }

}
