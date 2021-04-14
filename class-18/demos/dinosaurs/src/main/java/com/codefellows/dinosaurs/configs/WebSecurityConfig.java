package com.codefellows.dinosaurs.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImplementation userDetailsService;

    @Bean  // reusable component that loads once, reusable passwordEncoder
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean // if I need this authentication manager, I can @Autowire an authenticationmanager
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder // attach out userDetailsService to the auth Manager and the passwordEncoder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override // overloads the `configure method`
    public void configure(HttpSecurity httpSecurity) throws Exception {
//        We do all the work to decide how users can access the site
        httpSecurity
                .cors().disable()
                .csrf().disable() // cross site resource forgery (heroku needs this)

                .authorizeRequests()
                .antMatchers("/").permitAll() // allow access here
                .antMatchers("/dinosaurUser", "/login").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers(HttpMethod.GET, "/dinosaurUser/*").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .defaultSuccessUrl("/")
                .loginPage("/login") // any unauthorised request goes here

                .and()
                .logout()
                .logoutSuccessUrl("/"); // allows you to visit /logout and be logged out

    }
}
