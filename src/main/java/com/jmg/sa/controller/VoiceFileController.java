/**
 * 
 */
package com.jmg.sa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jmg.sa.domain.VoiceFile;
import com.jmg.sa.service.VoiceFileService;

/**
 * @author Javier Moreno Garcia
 *
 */
@Controller
@RequestMapping("/files")
public class VoiceFileController {

    @Inject
    private VoiceFileService voiceFileService;

    @RequestMapping(value = "/upload", method = GET)
    public String home() {
        return "upload";
    }

    @RequestMapping(value = "/upload", method = POST)
    public ModelAndView upload(@RequestPart("file") MultipartFile file) throws IOException {

        // TODO validate

        // execute service
        voiceFileService.addNewFile(file);

        // prepare response

        ModelAndView mv = new ModelAndView("upload");
        mv.addObject("success", true);

        return mv;
    }

    @RequestMapping(value = "/list", method = GET)
    public ModelAndView list(Pageable pageable) {

        // execute service
        Page<VoiceFile> response = voiceFileService.listFiles(pageable);

        // prepare response

        ModelAndView mv = new ModelAndView("list");
        mv.addObject("response", response);

        return mv;
    }
}
