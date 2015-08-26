/**
 * 
 */
package com.jmg.sa.service;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmg.sa.domain.VoiceFileKeyword;
import com.jmg.sa.repository.VoiceFileKeywordRepository;
import com.jmg.sa.util.SecurityUtils;

/**
 * @author Javier Moreno Garcia
 *
 */
@Service
@Transactional(readOnly = true)
public class VoiceFileKeywordServiceImpl implements VoiceFileKeywordService{

    @Inject
    private VoiceFileKeywordRepository voiceFileKeywordRepository;

    @Override
    public Page<VoiceFileKeyword> listFiles(Long voiceFileId, Pageable pageable) {
        
        // get logged user's email
        String loggedUserEmail = SecurityUtils.getLoggedUserEmail();

        return voiceFileKeywordRepository.findByVoiceFile_idAndUser_email(voiceFileId, loggedUserEmail, pageable);
    }
}
