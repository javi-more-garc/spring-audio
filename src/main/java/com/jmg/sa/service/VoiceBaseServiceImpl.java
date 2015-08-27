/**
 * 
 */
package com.jmg.sa.service;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

import java.io.ByteArrayInputStream;
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
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jmg.sa.bean.FileUploadResponse;
import com.jmg.sa.bean.GenericResponse;
import com.jmg.sa.bean.GetFileAnalyticsResponse;
import com.jmg.sa.bean.GetFileKeywordsResponse;
import com.jmg.sa.bean.GetFileStatusResponse;
import com.jmg.sa.bean.ListFolderResponse;
import com.jmg.sa.domain.VoiceFileContent;

/**
 * @author Javier Moreno Garcia
 *
 */
@Service
public class VoiceBaseServiceImpl implements VoiceBaseService {

    private final Logger logger = LoggerFactory.getLogger(VoiceBaseServiceImpl.class);

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
    public FileUploadResponse addNewFile(VoiceFileContent content) throws IOException {

        File tempFile = createTempFile(content);

        // create parameters
        MultiValueMap<String, Object> map = createParametersNewFile(content.getVoiceFile().getId(), tempFile);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        // perform request to audio service

        logger.info("Adding file with external id [{}]", content.getVoiceFile().getId());

        ResponseEntity<FileUploadResponse> response = restTemplate.exchange(AUDIO_SERVICE_URL, POST, requestEntity,
                FileUploadResponse.class);

        FileUploadResponse body = response.getBody();

        logger.info("Received response status [{}] with body [{}]", response.getStatusCode(), body);

        return body.isSuccess() ? body : null;
    }

    @Override
    public ListFolderResponse listFiles() {

        // create parameters
        MultiValueMap<String, String> map = createParametersListFiles();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(AUDIO_SERVICE_URL).queryParams(map);

        // perform request to audio service

        logger.info("Listing files");

        ResponseEntity<ListFolderResponse> response = restTemplate.getForEntity(builder.toUriString(),
                ListFolderResponse.class);

        ListFolderResponse body = response.getBody();

        logger.info("Received response status [{}] with body [{}]", response.getStatusCode(), body);

        return body;

    }

    @Override
    public boolean isProcessed(String mediaId) {
        // create parameters
        MultiValueMap<String, String> map = createParametersGetStatus(mediaId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(AUDIO_SERVICE_URL).queryParams(map);

        // perform request to audio service

        logger.info("Getting status of file with media id [{}]", mediaId);

        ResponseEntity<GetFileStatusResponse> response = restTemplate.getForEntity(builder.toUriString(),
                GetFileStatusResponse.class);

        GetFileStatusResponse body = response.getBody();

        logger.info("Received response status [{}] with body [{}]", response.getStatusCode(), body);

        return body.isSuccess() && body.isProcessed();
    }

    @Override
    public GetFileKeywordsResponse getKeywordsContent(String mediaId) {

        // create parameters
        MultiValueMap<String, String> map = createParametersGetKeywords(mediaId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(AUDIO_SERVICE_URL).queryParams(map);

        // perform request to audio service

        logger.info("Getting keywords of file with media id [{}]", mediaId);

        ResponseEntity<GetFileKeywordsResponse> response = restTemplate.getForEntity(builder.toUriString(),
                GetFileKeywordsResponse.class);

        GetFileKeywordsResponse body = response.getBody();

        logger.info("Received response status [{}] with body [{}]", response.getStatusCode(), body);

        return body.isSuccess() ? body : null;
    }

    @Override
    public GetFileAnalyticsResponse getAnalyticsContent(String mediaId) {
        // create parameters
        MultiValueMap<String, String> map = createParametersGetAnalytics(mediaId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(AUDIO_SERVICE_URL).queryParams(map);

        // perform request to audio service

        logger.info("Getting analytics of file with media id [{}]", mediaId);

        ResponseEntity<GetFileAnalyticsResponse> response = restTemplate.getForEntity(builder.toUriString(),
                GetFileAnalyticsResponse.class);

        GetFileAnalyticsResponse body = response.getBody();

        logger.info("Received response status [{}] with body [{}]", response.getStatusCode(), body);

        return body.isSuccess() ? body : null;
    }

    @Override
    public boolean delete(String mediaId) {
        // create parameters
        MultiValueMap<String, String> map = createParametersDelete(mediaId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(AUDIO_SERVICE_URL).queryParams(map);

        // perform request to audio service

        logger.info("Deleting file with media id [{}]", mediaId);

        ResponseEntity<GenericResponse> response = restTemplate.getForEntity(builder.toUriString(), GenericResponse.class);

        GenericResponse body = response.getBody();

        logger.info("Received response status [{}] with body [{}]", response.getStatusCode(), body);
                
        return body.isSuccess();
    }

    //
    // private methods

    private File createTempFile(VoiceFileContent content) throws IOException {

        String originalFilename = content.getVoiceFile().getFilename();

        String prefix = FilenameUtils.getBaseName(originalFilename);
        String suffix = FilenameUtils.getExtension(originalFilename);

        // create tmp file
        File tempFile = File.createTempFile(prefix, "." + suffix);

        // copy into tmp file the passed contentsO
        IOUtils.copyLarge(new ByteArrayInputStream(content.getBytes()), new FileOutputStream(tempFile));
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

    private MultiValueMap<String, String> createParametersGetStatus(String mediaId) {

        // create parameters
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        // version (mandatory)
        map.add("version", version);

        // api key (mandatory)
        map.add("apikey", apiKey);

        // password (mandatory)
        map.add("password", password);

        // action (mandatory)
        map.add("action", "getFileStatus");

        // media id (optional)
        map.add("mediaID", mediaId);

        return map;

    }

    private MultiValueMap<String, String> createParametersGetKeywords(String mediaId) {

        // create parameters
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        // version (mandatory)
        map.add("version", version);

        // api key (mandatory)
        map.add("apikey", apiKey);

        // password (mandatory)
        map.add("password", password);

        // action (mandatory)
        map.add("action", "getKeywords");

        // media id (optional)
        map.add("mediaID", mediaId);

        return map;

    }

    private MultiValueMap<String, String> createParametersGetAnalytics(String mediaId) {
        // create parameters
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        // version (mandatory)
        map.add("version", version);

        // api key (mandatory)
        map.add("apikey", apiKey);

        // password (mandatory)
        map.add("password", password);

        // action (mandatory)
        map.add("action", "getFileAnalyticsSnippets");

        // media id (optional)
        map.add("mediaID", mediaId);

        // return categories (optional)
        map.add("returnCategories", "1");

        // include start times categories (optional)
        map.add("includeStartTimes", "true");

        // return groups (optional)
        map.add("returnGroups", "false");

        return map;
    }

    private MultiValueMap<String, String> createParametersDelete(String mediaId) {
        // create parameters
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        // version (mandatory)
        map.add("version", version);

        // api key (mandatory)
        map.add("apikey", apiKey);

        // password (mandatory)
        map.add("password", password);

        // action (mandatory)
        map.add("action", "deleteFile");

        // media id (optional)
        map.add("mediaID", mediaId);

        return map;
    }

}
