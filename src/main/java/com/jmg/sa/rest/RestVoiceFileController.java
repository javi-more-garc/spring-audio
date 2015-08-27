/**
 * 
 */
package com.jmg.sa.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmg.sa.domain.VoiceFile;
import com.jmg.sa.service.VoiceFileService;

/**
 * @author Javier Moreno Garcia
 *
 */
@RestController
@RequestMapping("/api/files")
public class RestVoiceFileController {

    @Inject
    private VoiceFileService voiceFileService;

    @RequestMapping(value = "/all", method = GET)
    public List<VoiceFile> list() {

        // execute service
        return voiceFileService.listFilesForLoggedUser();
    }
    
    @RequestMapping(method = GET)
    public Page<VoiceFile> list(Pageable pageable) {

        // execute service
        return voiceFileService.listFilesForLoggedUser(pageable);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public VoiceFile findOne(@PathVariable Long id) {

        // execute service
        return voiceFileService.findOneForLoggedUser(id);
    }

}
