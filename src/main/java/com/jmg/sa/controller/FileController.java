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

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.io.Files;
import com.jmg.sa.bean.GenericResponse;
import com.jmg.sa.bean.ListFolderResponse;
import com.jmg.sa.service.AudioService;

/**
 * @author Javier Moreno Garcia
 *
 */
@Controller
@RequestMapping("/files")
public class FileController {

    @Inject
    private AudioService audioService;

    @RequestMapping(value = "/upload", method = GET)
    public String home() {
        return "upload";
    }

    @RequestMapping(value = "/upload", method = POST)
    public ModelAndView upload(@RequestPart("file") MultipartFile file) throws IOException {

        // TODO validate

        File tempFile = createTempFile(file);

        // execute service
        GenericResponse response = audioService.addNewFile(tempFile);

        // prepare response

        ModelAndView mv = new ModelAndView("upload");
        mv.addObject("response", response);

        return mv;
    }

    @RequestMapping(value = "/list", method = GET)
    public ModelAndView list() {

        // execute service
        ListFolderResponse response = audioService.listFiles();

        // prepare response

        ModelAndView mv = new ModelAndView("list");
        mv.addObject("response", response);

        return mv;
    }

    //
    // private methods

    private File createTempFile(MultipartFile file) throws IOException, FileNotFoundException {

        String originalFilename = file.getOriginalFilename();

        // create tmp dir/ file
        File tempDir = Files.createTempDir();
        File tempFile = new File(tempDir, originalFilename);

        tempDir.deleteOnExit();
        tempFile.deleteOnExit();

        // copy into tmp file the passed contentsO
        IOUtils.copyLarge(file.getInputStream(), new FileOutputStream(tempFile));
        return tempFile;
    }

}
