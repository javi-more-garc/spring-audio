/**
 * 
 */
package com.jmg.sa.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmg.sa.domain.VoiceFileKeywordAnalytics;
import com.jmg.sa.repository.VoiceFileKeywordAnalyticsRepository;
import com.jmg.sa.util.SecurityUtils;

/**
 * @author Javier Moreno Garcia
 *
 */
@Service
@Transactional(readOnly = true)
public class VoiceFileKeywordAnalyticsServiceImpl implements VoiceFileKeywordAnalyticsService{

    @Inject
    private VoiceFileKeywordAnalyticsRepository repository;
    
    @Override
    public List<VoiceFileKeywordAnalytics> listKeywordsForLoggedUser(Long voiceFileId) {

        // get logged user's email
        String loggedUserEmail = SecurityUtils.getLoggedUserEmail();

        // find using repository
        List<VoiceFileKeywordAnalytics> result = repository.findByVoiceFile_idAndUser_email(voiceFileId, loggedUserEmail);

        return result;

    }

    @Override
    public Page<VoiceFileKeywordAnalytics> listKeywordsForLoggedUser(Long voiceFileId, Pageable pageable) {
        
        // get logged user's email
        String loggedUserEmail = SecurityUtils.getLoggedUserEmail();

        return repository.findByVoiceFile_idAndUser_email(voiceFileId, loggedUserEmail, pageable);
    }
    
    @Override
    public List<VoiceFileKeywordAnalytics> searchKeywordsForLoggedUser(Long voiceFileId, String term) {
        
        // get logged user's email
        String loggedUserEmail = SecurityUtils.getLoggedUserEmail();        

        // TODO use all terms
        return repository.findByVoiceFile_idAndUser_emailAndName(voiceFileId, loggedUserEmail, term);
    }
}
