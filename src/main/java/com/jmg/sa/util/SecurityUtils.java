/**
 * 
 */
package com.jmg.sa.util;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Javier Moreno Garcia
 *
 */
public final class SecurityUtils {
   
    private SecurityUtils(){
        
    }
    
    public static String getLoggedUserEmail(){        
        return SecurityContextHolder.getContext().getAuthentication().getName();     
    }

}
