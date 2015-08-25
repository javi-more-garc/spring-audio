/**
 * 
 */
package com.jmg.sa;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * @author Javier Moreno Garcia
 *
 */
public class SpringAudioServletInitializer extends SpringBootServletInitializer{
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringAudioApplication.class);
    }

}
