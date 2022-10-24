package com.chornobuk.flashcardsapi;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration implements WebMvcConfigurer {
//    AuthenticationManager authenticationManager;

    @Bean
    public PasswordEncoder passwordEncoder() {
//        todo: change leven of encryption
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }



    //    todo: add filter for enabling cors request and for JWT implementation
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
////        http.authorizeHttpRequests().mvcMatchers("/languages").permitAll();
//        http.authorizeRequests().mvcMatchers("/languages", "/auth/login").permitAll();
//        http.authorizeRequests().anyRequest().authenticated();
////                .authorizeHttpRequests().anyRequest().permitAll()
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////                .and()
//        http.addFilter(new CustomAuthenticationFilter(authenticationManager));
////        http.addFilterBefore(new CustomAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }


}
