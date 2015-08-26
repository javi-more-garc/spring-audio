package com.jmg.sa.service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Component;

import com.jmg.sa.domain.User;
import com.jmg.sa.domain.VoiceFile;
import com.jmg.sa.exception.OperationNotAuthorizedException;
import com.jmg.sa.repository.UserRepository;
import com.jmg.sa.repository.VoiceFileRepository;
import com.jmg.sa.util.SecurityUtils;

/**
 * 
 * @author Javier Moreno Garcia
 *
 */
@Component
public class EntityFinderSupport {

    @Inject
    private UserRepository userRepository;

    @Inject
    private VoiceFileRepository voiceFileRepository;

    public User checkLoggedUserExistAndReturn() {

        String loggedUserEmail = SecurityUtils.getLoggedUserEmail();

        User existingUser = userRepository.findByEmailIgnoreCase(loggedUserEmail);

        // if there is no user with that email
        if (existingUser == null) {
            // throw problem
            throw new EntityNotFoundException(String.format("There is no user with email [%s]", loggedUserEmail));
        }

        return existingUser;
    }

    public VoiceFile checkVoiceFileExistAndReturn(Long id) {

        VoiceFile existingVoiceFile = voiceFileRepository.findOne(id);

        // if there is no voice file with that email
        if (existingVoiceFile == null) {
            // throw problem
            throw new EntityNotFoundException(String.format("There is no voice file with id [%d]", id));
        }
        
        String loggedUserEmail = SecurityUtils.getLoggedUserEmail();
        
        // if the user related to the voice file has a different mail
        if (!existingVoiceFile.getUser().getEmail().equals(loggedUserEmail)) {
            // throw problem
            throw new OperationNotAuthorizedException(
                    String.format("The location with id [%d] does not belong to user with email [%s]", id, loggedUserEmail));
        }        

        return existingVoiceFile;
    }

}
