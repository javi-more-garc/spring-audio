/**
 * 
 */
package com.jmg.sa.rest;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmg.sa.domain.VoiceFile;
import com.jmg.sa.domain.VoiceFileContent;
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

    @RequestMapping(method = GET)
    public Page<VoiceFile> list(Pageable pageable) {

        // execute service
        return voiceFileService.listFiles(pageable);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public VoiceFile findOne(@PathVariable Long id) {

        // execute service
        return voiceFileService.findOne(id);
    }

    @RequestMapping(value = "/{id}/content", method = GET)
    public ResponseEntity<byte[]> fileContent(@PathVariable Long id, HttpServletResponse response) throws IOException {

        VoiceFileContent content = voiceFileService.getContent(id);

        byte[] bytes = content.getBytes();

        return ResponseEntity //
                .ok() //
                .contentLength(bytes.length) //
                .contentType(APPLICATION_OCTET_STREAM) //
                // needed for chrome so that the user can browse the media file
                .header("Accept-Ranges", "bytes") //
                .header("Content-Disposition", String.format("attachment; filename=voice_file_%d", id)) //
                .body(bytes);
    }

}
