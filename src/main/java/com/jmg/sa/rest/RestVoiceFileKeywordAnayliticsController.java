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

import com.jmg.sa.domain.VoiceFileKeywordAnalytics;
import com.jmg.sa.service.VoiceFileKeywordAnalyticsService;

/**
 * @author Javier Moreno Garcia
 *
 */
@RestController
@RequestMapping("/api/files")
public class RestVoiceFileKeywordAnayliticsController {

    @Inject
    private VoiceFileKeywordAnalyticsService service;
    
    @RequestMapping(value = "/{voiceFileId}/keywords_analytics/all", method = GET)
    public List<VoiceFileKeywordAnalytics> list(@PathVariable Long voiceFileId) {

        // execute service
        return service.listKeywordsForLoggedUser(voiceFileId);
    }    

    @RequestMapping(value = "/{voiceFileId}/keywords_analytics", method = GET)
    public Page<VoiceFileKeywordAnalytics> list(@PathVariable Long voiceFileId, Pageable pageable) {
        
     // execute service
        return service.listKeywordsForLoggedUser(voiceFileId, pageable); 
    }

}
