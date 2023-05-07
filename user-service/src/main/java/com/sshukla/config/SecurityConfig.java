package com.sshukla.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */


public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
