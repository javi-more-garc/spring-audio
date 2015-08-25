/**
 * 
 */
package com.jmg.sa.bean;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Javier Moreno Garcia
 *
 */
public class GenericResponse {
    
    private String requestStatus;
    
    private String statusMessage;

    public String getRequestStatus() {
        return this.requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    public boolean isSuccess(){
        return "SUCCESS".equalsIgnoreCase(requestStatus);
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
    }

}
