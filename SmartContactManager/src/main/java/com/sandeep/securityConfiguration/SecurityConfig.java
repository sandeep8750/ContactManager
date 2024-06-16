package com.sandeep.securityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sound.midi.Soundbank;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private SecurityCustomUserDetailService userDetailService;


    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(SecurityCustomUserDetailService userDetailService, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((httpRequest) -> {
            httpRequest.requestMatchers("/home", "/register", "/service").permitAll();
            httpRequest.requestMatchers("/user/**").authenticated();
            httpRequest.anyRequest().permitAll();
        });

        httpSecurity.formLogin((formlogin) -> {
            formlogin.loginPage("/login");
            formlogin.loginProcessingUrl("/authenticate");
            formlogin.defaultSuccessUrl("/user/dashboard", true);
            formlogin.failureUrl("/login?error=true");
            formlogin.usernameParameter("email");
            formlogin.passwordParameter("password");
        });

        httpSecurity.logout((logout) -> {
            logout.logoutUrl("/logout");
            logout.logoutSuccessUrl("/home");
            logout.invalidateHttpSession(true);
            logout.deleteCookies("JSESSIONID");
        });


        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        System.out.println("sandeep kumar prajapati");
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        System.out.println(userDetailService.toString());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }
}
