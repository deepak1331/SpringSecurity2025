package com.learn.SpringSecurity2025.config;

import com.learn.SpringSecurity2025.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {


    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return
                httpSecurity
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(authorization -> authorization.requestMatchers("/register").permitAll())
                        .authorizeHttpRequests(authorization -> authorization.anyRequest().authenticated())
                        //.formLogin(Customizer.withDefaults())
                        .httpBasic(Customizer.withDefaults())
                        .sessionManagement(session ->
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(myUserDetailsService);

        return provider;
    }

    /*@Bean
    public UserDetailsService userDetailsService() {

        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("Avyaan").password("pass").roles("ADMIN", "USER").build();

        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("Shipra").password("pass").roles("ADMIN", "USER").build();

        return new InMemoryUserDetailsManager(user1, user2);
    }*/
}
