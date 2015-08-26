/**
 * 
 */
package com.jmg.sa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.inject.Inject;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jmg.sa.domain.VoiceFile;
import com.jmg.sa.service.EntityFinderSupport;

/**
 * @author Javier Moreno Garcia
 *
 */
@Controller
@RequestMapping("/player")
public class PlayerController {
    
    @Inject
    private EntityFinderSupport efSupport;
        
    @RequestMapping(method = GET)
    public ModelAndView home(@RequestParam Long id) {
        
        VoiceFile voiceFile = efSupport.checkVoiceFileExistAndReturn(id);
                
        ModelAndView mv = new ModelAndView("player");
        mv.addObject("id", id);
        mv.addObject("extension", FilenameUtils.getExtension(voiceFile.getFilename()));        
        
        return mv;
    }
    

}
