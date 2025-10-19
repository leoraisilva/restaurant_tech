package br.com.fiap.restaurant.infra.config.security;

import br.com.fiap.restaurant.infra.adapter.outbound.exception.ResponseException;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.UsuarioJPARepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class UsuarioSecurity {
    private final SecurityFilter securityFilter;

    public UsuarioSecurity(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/usuario/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/usuario/create").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/usuario/delete/{username}").hasAnyRole("OWNER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/usuario/list/number/{number}/page/{page}").hasAnyRole("OWNER", "CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/usuario/reset").hasAnyRole("OWNER", "CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/usuario/update/{username}").hasAnyRole("OWNER", "CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/v1/usuario/search/{username}").hasAnyRole("OWNER", "CLIENT")
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioJPARepository repository) {
        return username -> {
            var user = repository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("Usuário não encontrado: " + username);
            }
            return user;
        };
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
