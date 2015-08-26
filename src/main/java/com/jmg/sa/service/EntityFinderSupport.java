package com.jmg.sa.service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Component;

import com.jmg.sa.domain.User;
import com.jmg.sa.repository.UserRepository;
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

 
}
