package cz.ales17.auto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String rememberMeKey = System.getenv("REMEMBER_ME_KEY");
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login/**", "/webjars/**","robots.txt").permitAll()
                        .anyRequest().authenticated()//.permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .rememberMe(rememberMe -> rememberMe.key(rememberMeKey))
                .formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/",true))
                .logout(logout -> logout.logoutUrl("/logout"));

        return http.build();
    }
}