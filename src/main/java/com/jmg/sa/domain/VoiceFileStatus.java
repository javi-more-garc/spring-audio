/**
 * 
 */
package com.jmg.sa.domain;

/**
 * @author Javier Moreno Garcia
 *
 */

public enum VoiceFileStatus {

    // just uploaded to our application
    UPLOADED_TO_APPLICATION, //
    
    // uploaded to our application and to voice base (we have already media id)
    UPLOADED_TO_VOICE_BASE, //
    
    // processed in voice base
    PROCESSED_IN_VOICE_BASE, //
    
    // processed in voice base and all its details synchronized in our
    // application (file still exits in voice base)
    SYNCHRONIZED, //
    
    // processed (file no longer in voice base)
    PROCESSED, //

}
