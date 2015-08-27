/**
 * 
 */
package com.jmg.sa.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jmg.sa.domain.AbstractVoiceFileKeyword;
import com.jmg.sa.domain.VoiceFileContent;
import com.jmg.sa.service.EntityFinderSupport;
import com.jmg.sa.service.VoiceFileKeywordAnalyticsService;
import com.jmg.sa.service.VoiceFileKeywordTranscriptService;

/**
 * @author Javier Moreno Garcia
 *
 */
@RestController
@RequestMapping("/api/wrapper/files")
public class RestVoiceBaseWrapperController {
    
    @Inject
    private EntityFinderSupport efSupport;
    
    @Inject
    private VoiceFileKeywordAnalyticsService voiceFileKeywordAnalyticsService;
    
    @Inject
    private VoiceFileKeywordTranscriptService voiceFileKeywordTranscriptService;    

    @RequestMapping(value = "/{voiceFileId}", method = GET)
    public ResponseEntity<Map<String, Object>> request(@PathVariable Long voiceFileId, @RequestParam String action)
            throws IOException {

        Map<String, Object> map = new HashMap<>();

        return ResponseEntity.status(HttpStatus.OK).body(map);

    }

    @RequestMapping(value = "/{voiceFileId}", params = { "action=getKeywords" }, method = GET)
    public ResponseEntity<Map<String, Object>> getKeywords(@PathVariable Long voiceFileId, @RequestParam String action)
            throws IOException {
        
        VoiceFileContent voiceFileContent = efSupport.checkVoiceFileContentExistAndReturn(voiceFileId);

        Map<String, Object> map = getContentAsMap(voiceFileContent.getKeywords());

        return ResponseEntity.status(HttpStatus.OK).body(map);

    }

    @RequestMapping(value = "/{voiceFileId}", params = { "action=getFileAnalyticsSnippets" }, method = GET)
    public ResponseEntity<Map<String, Object>> getFileAnalyticsSnippets(@PathVariable Long voiceFileId,
            @RequestParam String action) throws IOException {

        VoiceFileContent voiceFileContent = efSupport.checkVoiceFileContentExistAndReturn(voiceFileId);

        Map<String, Object> map = getContentAsMap(voiceFileContent.getAnalytics());
        
        return ResponseEntity.status(HttpStatus.OK).body(map);

    }

    @RequestMapping(value = "/{voiceFileId}", params = { "action=searchFile" }, method = GET)
    public ResponseEntity<Map<String, Object>> searchFiles(@PathVariable Long voiceFileId, @RequestParam String action,
            @RequestParam List<String> terms) throws IOException {
        
        // TOD handle many
        String term = terms.iterator().next();
        
        // first search keywords in analytics
        List<? extends AbstractVoiceFileKeyword> keywords = voiceFileKeywordAnalyticsService.searchKeywordsForLoggedUser(voiceFileId, term);
        
        // if nothing there
        if (keywords.isEmpty()){
            
            // search keywords in transcript
            keywords = voiceFileKeywordTranscriptService.searchKeywordsForLoggedUser(voiceFileId, term);
        }
    
        Map<String, Object> result = getSearchResponse(keywords);

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    //
    // private

    private Map<String, Object> getContentAsMap(String content) throws IOException {

        Map<String, Object> map = new Gson().fromJson(content, new TypeToken<HashMap<String, Object>>() {
        }.getType());

        return map;
    }

    private Map<String, Object> getSearchResponse(List<? extends AbstractVoiceFileKeyword> searchKeywords) {
        Map<String, Object> hits1 = new HashMap<>();        
        hits1.put("length",  "0");
        
        List<Map<String, Object>> hits2 = new ArrayList<>();
        hits1.put("hits", hits2);

        int pos = 0;

        if (!searchKeywords.isEmpty()) {

            for (AbstractVoiceFileKeyword keyword : searchKeywords) {

                Map<String, Object> hit3 = new HashMap<>();

                hit3.put("term", keyword.getName());

                List<Map<String, Object>> hit4 = new ArrayList<>();

                List<Double> timeValues = keyword.getTimeValues();

                for (Double time : timeValues) {

                    Map<String, Object> hit5 = new HashMap<>();

                    hit5.put("pos", Double.valueOf(pos + 1));
                    hit5.put("time", time);

                    hit4.add(hit5);

                    pos++;

                }

                hit3.put("hits", hit4);

                hits2.add(hit3);

            }

        }

        Map<String, Object> result = new HashMap<>();

        result.put("requestStatus", "SUCCESS");
        result.put("hits", hits1);
        result.put("numberOfHits", Double.valueOf(pos));

        return result;
    }

}
