package com.example.ApplicationService.config;

import com.example.ApplicationService.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private JwtFilter jwtFilter;

    @Autowired
    public void setJwtFilter(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/ApplicationService/upload").hasAuthority("Employee")
                .antMatchers("/ApplicationService/download/*").permitAll()
                .antMatchers("/ApplicationService/create").permitAll()

                .antMatchers("/ApplicationService/all/status").hasAuthority("HR")
                .antMatchers("/ApplicationService/update/{id}").hasAuthority("HR")

                .antMatchers("/ApplicationService/application/*").permitAll()
                .anyRequest()
                .authenticated();
    }
}