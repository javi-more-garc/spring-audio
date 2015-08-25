/**
 * 
 */
package com.jmg.sa.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Javier Moreno Garcia
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private DataSource dataSource;

    // This configuration does not restrict access and does not have csrf

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http //
                .headers().cacheControl().disable().and()//
                .csrf().disable()//
                .authorizeRequests() //
                .antMatchers("/login", "/logout").permitAll()//
                .anyRequest().authenticated() //
                .and().formLogin() //
                .loginPage("/login") //
                .loginProcessingUrl("/login") //
                .usernameParameter("email")//
                .passwordParameter("password")//
                .and().logout() //
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //
                .logoutSuccessUrl("/login") //
                .deleteCookies("remember") //
                .permitAll(); //

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select email, password, enabled from user where email=?")
                .authoritiesByUsernameQuery("select email, 'default' from user where email =?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
