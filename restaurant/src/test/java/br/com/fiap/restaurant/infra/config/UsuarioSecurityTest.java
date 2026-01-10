package br.com.fiap.restaurant.infra.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.UsuarioJPARepository;
import br.com.fiap.restaurant.infra.config.security.SecurityFilter;
import br.com.fiap.restaurant.infra.config.security.TokenService;
import br.com.fiap.restaurant.infra.config.security.UsuarioSecurity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UsuarioSecurity.class)
@TestPropertySource(properties = "api.security.token.secret=test-secret-key")
@Import({UsuarioSecurity.class, SecurityFilter.class, TokenService.class})
class UsuarioSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UsuarioJPARepository repository;

    @Test
    @DisplayName("Deve permitir acesso ao login sem autenticação")
    void devePermitirLogin() throws Exception {
        mockMvc.perform(post("/api/v1/usuario/auth/login"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve negar acesso a criação de restaurante para usuários sem ROLE_OWNER")
    @WithMockUser(roles = "CLIENT")
    void deveNegarCriacaoRestauranteParaCliente() throws Exception {
        mockMvc.perform(post("/api/v1/restaurant/create"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Deve permitir acesso a criação de restaurante para ROLE_OWNER")
    @WithMockUser(roles = "OWNER")
    void devePermitirCriacaoRestauranteParaOwner() throws Exception {
        mockMvc.perform(post("/api/v1/restaurant/create"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve retornar 403 (Forbidden) ao tentar deletar usuário sem estar logado")
    void deveNegarDeleteSemLogin() throws Exception {
        mockMvc.perform(delete("/api/v1/usuario/delete/teste"))
                .andExpect(status().isForbidden());
    }
}