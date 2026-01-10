package br.com.fiap.restaurant.infra.config;

import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.UsuarioEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.UsuarioJPARepository;
import br.com.fiap.restaurant.infra.config.security.SecurityFilter;
import br.com.fiap.restaurant.infra.config.security.TokenService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SecurityTest {
    @InjectMocks
    private SecurityFilter securityFilter;

    @Mock
    private TokenService tokenService;

    @Mock
    private UsuarioJPARepository repository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    private final String SECRET_TESTE = "12345678";

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();

        tokenService = new TokenService();
        // Injeta o valor no campo privado @Value("${api.security.token.secret}")
        ReflectionTestUtils.setField(tokenService, "secret", SECRET_TESTE);
    }

    @Test
    void deveContinuarFiltroQuandoTokenForInexistente() throws Exception {
        when(request.getHeader("authorization")).thenReturn(null);


        securityFilter.doFilterInternal(request, response, filterChain);


        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }


    @Test
    void deveGerarToken() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsername("admin@restaurante.com");

        String token = tokenService.GenerationToken(usuario);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void deveValidarTokenERetornarSubject() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsername("cliente@teste.com");

        String token = tokenService.GenerationToken(usuario);
        String subject = tokenService.validationToken(token);

        assertEquals("cliente@teste.com", subject);
    }

    @Test
    void deveLancarExcecaoTokenInvalido() {
        String tokenInvalido = "token.totalmente.errado";

        assertThrows(JWTVerificationException.class, () -> {
            tokenService.validationToken(tokenInvalido);
        });
    }

}
