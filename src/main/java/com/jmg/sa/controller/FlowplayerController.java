/**
 * 
 */
package com.jmg.sa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Javier Moreno Garcia
 *
 */
@Controller
@RequestMapping("/flowplayer")
public class FlowplayerController {
        
    @RequestMapping(method = GET)
    public ModelAndView home(@RequestParam Long id) {
                
        ModelAndView mv = new ModelAndView("flowplayer");
        mv.addObject("id", id);
        
        return mv;
    }
    

}
