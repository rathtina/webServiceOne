package org.springboot.authapi.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private  JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration=new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8080"));
        configuration.setAllowedMethods(List.of("GET", "POST"));
        configuration.setAllowedHeaders(List.of("authorization","content-type"));

        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


}

//The authorizeHttpRequests() method in Spring Security is used to configure access control for different
//HTTP endpoints in your application. It determines which requests are allowed or denied based on authentication
//and authorization rules.

//----------------------------------------------sessionManagement-----------------------------------
//1-sessionCreationPolicy(SessionCreationPolicy.STATELESS):No session is created. Good for REST APIs.(Recommended for REST APIs)
//2-sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED):Creates a session only if required (default behavior).
//3-sessionCreationPolicy(SessionCreationPolicy.ALWAYS):Always creates a session, even if not required.
//4-sessionCreationPolicy(SessionCreationPolicy.NEVER):Spring Security will never create a session, but it will use one if it exists.
//5-invalidSessionUrl("/session-expired"):Redirects the user when an invalid session is detected.
//6-maximumSessions(1).maxSessionsPreventsLogin(true):Limits the number of concurrent sessions per user.
//-------------------------------------Purpose of this class------------------------------------------
//The custom authentication is ready, and the remaining thing is to define what criteria an incoming request
//must match before being forwarded to application middleware. We want the following criteria:

//--------------------------------------------------------6---------------------------------------------