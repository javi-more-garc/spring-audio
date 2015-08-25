/**
 * 
 */
package com.jmg.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmg.sa.domain.User;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{
    
    User findByEmailIgnoreCase(String email);
    

}
