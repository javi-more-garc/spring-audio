/**
 * 
 */
package com.jmg.sa.service;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.jmg.sa.bean.GenericResponse;
import com.jmg.sa.bean.ListFolderResponse;

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

    @Value("${application.audio.service.folder}")
    private String folder;

    @Inject
    private RestTemplate restTemplate;

    @Override
    @Async
    public GenericResponse addNewFileAsync(Long externalId, MultipartFile file) throws IOException {

        File tempFile = createTempFile(file);

        // create parameters
        MultiValueMap<String, Object> map = createParametersNewFile(externalId, tempFile);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        // perform request to audio service

        ResponseEntity<GenericResponse> response = restTemplate.exchange(AUDIO_SERVICE_URL, POST, requestEntity,
                GenericResponse.class);

        GenericResponse body = response.getBody();

        logger.info("Received response status [{}] with body [{}]", response.getStatusCode(), body);

        return body;
    }

    @Override
    public ListFolderResponse listFiles() {

        // create parameters
        MultiValueMap<String, String> map = createParametersListFiles();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(AUDIO_SERVICE_URL).queryParams(map);

        // perform request to audio service
        ResponseEntity<ListFolderResponse> response = restTemplate.getForEntity(builder.toUriString(),
                ListFolderResponse.class);

        ListFolderResponse body = response.getBody();

        logger.info("Received response status [{}] with body [{}]", response.getStatusCode(), body);

        return body;

    }

    //
    // private methods

    private File createTempFile(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();

        String suffix = FilenameUtils.getBaseName(originalFilename);
        String prefix = FilenameUtils.getExtension(originalFilename);
        
        // create tmp file
        File tempFile = File.createTempFile(prefix, suffix);

        // copy into tmp file the passed contentsO
        IOUtils.copyLarge(file.getInputStream(), new FileOutputStream(tempFile));
        return tempFile;
    }

    private MultiValueMap<String, Object> createParametersNewFile(Long externalId, File file) {

        // create parameters
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

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

        // folder (optional)
        map.add("folderName", folder);

        // external id (optional)
        map.add("externalID", externalId);

        // TODO - Introduce callback so that we are called when the file is
        // processed

        // file (mandatory)
        map.add("file", new FileSystemResource(file));

        return map;

    }

    private MultiValueMap<String, String> createParametersListFiles() {

        // create parameters
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        // version (mandatory)
        map.add("version", version);

        // api key (mandatory)
        map.add("apikey", apiKey);

        // password (mandatory)
        map.add("password", password);
        // action (mandatory)
        map.add("action", "listFolder");

        // folder (mandatory)
        map.add("folderName", folder);

        return map;
    }

}
