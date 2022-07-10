package com.epam.cinema.spring.config;

import com.epam.cinema.spring.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Configuration
public class WebSecurityConfig {
    @Autowired
    private UserService userService;

    @Autowired
    private SuccessHandler successHandler;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<com.epam.cinema.spring.enity.User> user = userService.findUserByLogin(username);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException(username + " not found");
            }

            Set<GrantedAuthority> roles = new HashSet<>();
            roles.add(new SimpleGrantedAuthority("ROLE_" + user.get().getUserRole()));
            return new User(user.get().getUserLogin(), user.get().getUserPassword(), roles);
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/main", "/screening").permitAll()
                .antMatchers("/guest/**", "/login").anonymous()
                .antMatchers("/image/**", "/js/**", "/css/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/main")
                .and()
                .formLogin()
                .successHandler(successHandler)
                .loginPage("/login")
                .usernameParameter("userLogin")
                .passwordParameter("userPassword")
                .and()
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
        return http.build();
    }
}

