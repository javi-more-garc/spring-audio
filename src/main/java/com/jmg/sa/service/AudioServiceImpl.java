/**
 * 
 */
package com.jmg.sa.service;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

import java.io.File;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Javier Moreno Garcia
 *
 */
@Service
public class AudioServiceImpl implements AudioService {

    private final Logger logger = LoggerFactory.getLogger(AudioServiceImpl.class);

    private final static String AUDIO_SERVICE_URL = "https://api.voicebase.com/services";

    @Value("${application.audio.service.version}")
    private String version;

    @Value("${application.audio.service.apiKey}")
    private String apiKey;

    @Value("${application.audio.service.password}")
    private String password;

    @Inject
    private RestTemplate restTemplate;

    @Override
    public void addNewFile(File file) {

        // create parameters
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

        addParameters(map, file);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        // perform request to audio service

        ResponseEntity<String> response = restTemplate.exchange(AUDIO_SERVICE_URL, HttpMethod.POST, requestEntity,
                String.class);

        logger.info("Received response status [{}] with body [{}]", response.getStatusCode(), response.getBody());

    }
    //
    // private methods

    private void addParameters(LinkedMultiValueMap<String, Object> map, File file) {

        // version (mandatory)
        map.add("version", version);

        // api key (mandatory)
        map.add("apikey", apiKey);

        // password (mandatory)
        map.add("password", password);

        // action (mandatory)
        map.add("action", "uploadMedia");
        
        // transcriptType (mandatory)
        map.add("transcriptType", "machine");        
        
        // ownerID (optional)
        //map.add("ownerID", SecurityUtils.getLoggedUserEmail());                       

        // file (mandatory)
        map.add("file", new FileSystemResource(file));

    }

}
