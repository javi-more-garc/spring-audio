/**
 * 
 */
package com.jmg.sa.schedule;

import static com.jmg.sa.domain.VoiceFileStatus.PROCESSED;
import static com.jmg.sa.domain.VoiceFileStatus.PROCESSED_IN_VOICE_BASE;
import static com.jmg.sa.domain.VoiceFileStatus.SENT_TO_VOICE_BASE;
import static com.jmg.sa.domain.VoiceFileStatus.SYNCHRONIZED;
import static com.jmg.sa.domain.VoiceFileStatus.UPLOADED;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jmg.sa.bean.FileUploadResponse;
import com.jmg.sa.bean.GetFileAnalyticsResponse;
import com.jmg.sa.bean.GetFileKeywordsResponse;
import com.jmg.sa.domain.VoiceFile;
import com.jmg.sa.domain.VoiceFileContent;
import com.jmg.sa.service.VoiceBaseService;
import com.jmg.sa.service.VoiceFileContentService;
import com.jmg.sa.service.VoiceFileService;

/**
 * @author Javier Moreno Garcia
 *
 */
@Component
public class VoiceBaseSynchronizer {

    @Inject
    private VoiceBaseService voiceBaseService;

    @Inject
    private VoiceFileService voiceFileService;

    @Inject
    private VoiceFileContentService voiceFileContentService;

    @Scheduled(fixedRate = 30000, initialDelay = 5000)
    public void sendUploadedFilesToVoiceBase() throws IOException {

        // get all files with status sent
        List<VoiceFile> listFiles = voiceFileService.listFiles(UPLOADED);

        // iterate through them
        for (VoiceFile voiceFile : listFiles) {

            Long id = voiceFile.getId();

            // get content
            VoiceFileContent content = voiceFileContentService.getContent(id);

            // send it to voice base
            FileUploadResponse response = voiceBaseService.addNewFile(content);

            if (response != null) {
                // update media id and status (to SENT_TO_VOICE_BASE)
                voiceFileService.update(id, response);
            }

        }

    }

    @Scheduled(fixedRate = 30000, initialDelay = 10000)
    public void retrieveFileStatusesFromVoiceBase() throws IOException {

        // get all files with status sent
        List<VoiceFile> listFiles = voiceFileService.listFiles(SENT_TO_VOICE_BASE);

        // iterate through them
        for (VoiceFile voiceFile : listFiles) {

            Long id = voiceFile.getId();
            String mediaId = voiceFile.getMediaId();

            // if voice file is processed in voice base
            if (voiceBaseService.isProcessed(mediaId)){
                
                // update status
                voiceFileService.updateStatus(id, PROCESSED_IN_VOICE_BASE);
            }

        }

    }

    @Scheduled(fixedRate = 30000, initialDelay = 15000)
    public void retrieveFileInfoFromVoiceBase() throws IOException {

        // get all files with status sent
        List<VoiceFile> listFiles = voiceFileService.listFiles(PROCESSED_IN_VOICE_BASE);

        // iterate through them
        for (VoiceFile voiceFile : listFiles) {

            Long id = voiceFile.getId();
            String mediaId = voiceFile.getMediaId();

            // get keywords content from voice base
            GetFileKeywordsResponse keywords = voiceBaseService.getKeywordsContent(mediaId);

            // get keywords content from voice base
            GetFileAnalyticsResponse analytics = voiceBaseService.getAnalyticsContent(mediaId);

            // if values are ready and returned
            if (keywords != null && analytics != null) {

                // update keyword/ analytics and status (to SYNCHRONIZED)
                voiceFileService.update(id, keywords, analytics);
            }

        }

    }

    @Scheduled(fixedRate = 30000, initialDelay = 20000)
    public void deleteFilesFromVoiceBase() throws IOException {

        // get all files with status sent
        List<VoiceFile> listFiles = voiceFileService.listFiles(SYNCHRONIZED);

        // iterate through them
        for (VoiceFile voiceFile : listFiles) {

            Long id = voiceFile.getId();
            String mediaId = voiceFile.getMediaId();

            // if files could be deleted from voice base
            if (voiceBaseService.delete(mediaId)) {

                // update status
                voiceFileService.updateStatus(id, PROCESSED);
            }

        }

    }

}
