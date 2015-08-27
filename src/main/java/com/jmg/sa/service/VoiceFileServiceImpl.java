/**
 * 
 */
package com.jmg.sa.service;

import static com.jmg.sa.domain.VoiceFileStatus.SENT_TO_VOICE_BASE;
import static com.jmg.sa.domain.VoiceFileStatus.SYNCHRONIZED;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.jmg.sa.bean.FileUploadResponse;
import com.jmg.sa.bean.GetFileAnalyticsResponse;
import com.jmg.sa.bean.GetFileAnalyticsResponse.Keyword;
import com.jmg.sa.bean.GetFileKeywordsResponse;
import com.jmg.sa.bean.GetFileKeywordsResponse.Transcript;
import com.jmg.sa.domain.User;
import com.jmg.sa.domain.VoiceFile;
import com.jmg.sa.domain.VoiceFileContent;
import com.jmg.sa.domain.VoiceFileKeywordAnalytics;
import com.jmg.sa.domain.VoiceFileKeywordTrascript;
import com.jmg.sa.domain.VoiceFileStatus;
import com.jmg.sa.repository.VoiceFileRepository;
import com.jmg.sa.util.SecurityUtils;

/**
 * @author Javier Moreno Garcia
 *
 */
@Service
@Transactional(readOnly = true)
public class VoiceFileServiceImpl implements VoiceFileService {

    @Inject
    private VoiceFileRepository voiceFileRepository;

    @Inject
    private EntityFinderSupport efSupport;

    @Override
    public List<VoiceFile> listFiles(VoiceFileStatus status) {

        // find using repository
        List<VoiceFile> result = voiceFileRepository.findByStatus(status);

        return result;
    }

    @Override
    public List<VoiceFile> listFilesForLoggedUser() {

        // get logged user's email
        String loggedUserEmail = SecurityUtils.getLoggedUserEmail();

        // find using repository
        List<VoiceFile> result = voiceFileRepository.findByUser_email(loggedUserEmail);

        return result;
    }

    @Override
    public Page<VoiceFile> listFilesForLoggedUser(Pageable pageable) {

        // get logged user's email
        String loggedUserEmail = SecurityUtils.getLoggedUserEmail();

        // find using repository
        Page<VoiceFile> result = voiceFileRepository.findByUser_email(loggedUserEmail, pageable);

        return result;

    }

    @Override
    public VoiceFile findOneForLoggedUser(Long id) {
        // get voice file
        VoiceFile voiceFile = efSupport.checkVoiceFileExistAndReturn(id);

        // return it
        return voiceFile;
    }

    @Override
    @Transactional(readOnly = false)
    public void addNewFileForLoggedUser(MultipartFile file) throws IOException {

        // get logged user
        User loggedUser = efSupport.checkLoggedUserExistAndReturn();

        // create new voice file content entity
        VoiceFileContent voiceFileContent = new VoiceFileContent(file.getBytes(), loggedUser);

        // create new voice file entity
        VoiceFile voiceFile = new VoiceFile(file.getOriginalFilename(), loggedUser);

        // assign one-to-one relation
        voiceFile.setContent(voiceFileContent);
        voiceFileContent.setVoiceFile(voiceFile);

        // save file
        voiceFileRepository.save(voiceFile);

    }

    @Override
    @Transactional(readOnly = false)
    public void update(Long id, FileUploadResponse response) {

        // get voice file
        VoiceFile voiceFile = voiceFileRepository.findOne(id);
        voiceFile.setMediaId(response.getMediaId());

        // update status
        voiceFile.setStatus(SENT_TO_VOICE_BASE);

        // save file
        voiceFileRepository.save(voiceFile);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Long id, GetFileKeywordsResponse trascript, GetFileAnalyticsResponse analytics) {

        // get voice file
        VoiceFile voiceFile = voiceFileRepository.findOne(id);

        // get content
        VoiceFileContent content = voiceFile.getContent();

        //
        // keywords in transcript response

        for (Transcript transcriptItem : trascript.getTranscript()) {

            // if dealing with a delimiter
            if (transcriptItem.getM() != null) {
                // continue with the next one
                continue;
            }

            VoiceFileKeywordTrascript keyword = new VoiceFileKeywordTrascript(transcriptItem.getW(), Collections.singletonList(transcriptItem.getS()),
                    voiceFile.getUser());
            
            keyword.setVoiceFile(voiceFile);

            voiceFile.getTranscriptKeywords().add(keyword);

        }

        //
        // keywords in analytics response

        for (Keyword keywordItem : analytics.getKeywords()) {

            Map<String, List<Double>> t = keywordItem.getT();

            // TODO be careful with unknown
            VoiceFileKeywordAnalytics keyword = new VoiceFileKeywordAnalytics(keywordItem.getName(), t.get("unknown"),
                    voiceFile.getUser());
            keyword.setVoiceFile(voiceFile);

            voiceFile.getAnalyticsKeywords().add(keyword);

        }

        // create gson
        Gson gson = new Gson();

        // update content values
        content.setKeywords(gson.toJson(trascript));
        content.setAnalytics(gson.toJson(analytics));

        // update status
        voiceFile.setStatus(SYNCHRONIZED);

        // save file
        voiceFileRepository.save(voiceFile);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateStatus(Long id, VoiceFileStatus status) {

        // get voice file
        VoiceFile voiceFile = voiceFileRepository.findOne(id);

        // update status
        voiceFile.setStatus(status);

        // save file
        voiceFileRepository.save(voiceFile);
    }

}
