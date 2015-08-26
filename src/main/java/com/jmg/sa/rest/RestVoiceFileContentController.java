/**
 * 
 */
package com.jmg.sa.rest;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmg.sa.domain.VoiceFileContent;
import com.jmg.sa.service.VoiceFileContentService;

/**
 * @author Javier Moreno Garcia
 *
 */
@RestController
@RequestMapping("/api/files")
public class RestVoiceFileContentController {

    @Inject
    private VoiceFileContentService voiceFileContentService;

    @RequestMapping(value = "/{voiceFileId}/content", method = GET)
    public ResponseEntity<byte[]> fileContent(@PathVariable Long voiceFileId, HttpServletResponse response)
            throws IOException {

        VoiceFileContent content = voiceFileContentService.getContent(voiceFileId);

        byte[] bytes = content.getBytes();

        return ResponseEntity //
                .ok() //
                .contentLength(bytes.length) //
                .contentType(APPLICATION_OCTET_STREAM) //
                // needed for chrome so that the user can browse the media file
                .header("Accept-Ranges", "bytes") //
                .header("Content-Disposition",
                        String.format("attachment; filename=voice_file_%d_content_%d", voiceFileId, content.getId())) //
                .body(bytes);
    }

}
