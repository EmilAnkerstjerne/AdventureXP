package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("myUserDetailsService")
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/work-schedule", "/work-schedule/*",
                        "/activities/deleteActivity/*", "/activities/create/save", "/activities", "/activities/create").hasRole("ADMIN")
                .antMatchers("/store", "/store/*", "/emenu", "/booking/*", "/booking", "/bookings").hasAnyRole("ADMIN", "USER")
                .antMatchers( "/registration/*", "/public/*").permitAll()
                .and().formLogin();

        http.
                logout(logout -> logout
                .logoutUrl("/logout")
                . addLogoutHandler(new SecurityContextLogoutHandler())
                );
        http.csrf().disable(); //To fix forbidden error, potential problem with using browsers TODO
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/public/**"); //Used as part of template static resources fix
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance(); //No password encryption, not production ready TODO
    }

}
