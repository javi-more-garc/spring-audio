/**
 * 
 */
package com.jmg.sa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jmg.sa.service.AudioService;

/**
 * @author Javier Moreno Garcia
 *
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @Inject
    private AudioService audioService;

    @RequestMapping(method = GET)
    public String uploadHome() {
        return "upload";
    }

    @RequestMapping(method = POST)
    public ModelAndView handleFileUpload(@RequestPart("file") MultipartFile file) throws IOException {

        // TODO validate

        File tempFile = createTempFile(file);

        audioService.addNewFile(tempFile);

        // prepare response

        ModelAndView mv = new ModelAndView("upload");
        mv.addObject("uploadSuccess", true);

        return mv;
    }

    //
    // private methods

    private File createTempFile(MultipartFile file) throws IOException, FileNotFoundException {

        String originalFilename = file.getOriginalFilename();

        String filename = FilenameUtils.getBaseName(originalFilename);
        String extension = "." + FilenameUtils.getExtension(originalFilename);

        // create tmp file
        File tempFile = File.createTempFile(filename, extension);

        // copy into tmp file the passed contentsO
        IOUtils.copyLarge(file.getInputStream(), new FileOutputStream(tempFile));
        return tempFile;
    }

}
