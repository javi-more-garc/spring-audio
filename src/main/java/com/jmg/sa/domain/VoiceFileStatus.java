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
    UPLOADED, //
    // sent to voice base
    SENT_TO_VOICE_BASE, //
    // processing in voice base
    PROCESSED_IN_VOICE_BASE, //    
    // processed but file already exits in voice base    
    SYNCHRONIZED, //
    // processed (file no longer in voice base)
    PROCESSED, //

}
