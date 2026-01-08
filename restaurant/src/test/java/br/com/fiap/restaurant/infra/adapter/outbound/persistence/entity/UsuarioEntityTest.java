package br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity;

import java.time.LocalDateTime;

import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.AddressEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.UsuarioEntity;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class UsuarioEntityTest {

    @Test
    void deveCriarUsuarioComConstrutorCompleto() {
        LocalDateTime agora = LocalDateTime.now();

        UsuarioEntity usuario = new UsuarioEntity(
                "João",
                "joao123",
                "senha123",
                "joao@email.com",
                Role.CLIENT,
                "12345-678",
                100,
                true,
                agora,
                agora
        );

        assertEquals("João", usuario.getNome());
        assertEquals("joao123", usuario.getUsername());
        assertEquals("senha123", usuario.getSenha());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals(Role.CLIENT, usuario.getRegras());
        assertEquals("12345-678", usuario.getCEP());
        assertEquals(100, usuario.getNumero());
        assertTrue(usuario.isActived());
        assertEquals(agora, usuario.getCreatedAt());
        assertEquals(agora, usuario.getModifiedAt());
    }

    @Test
    void devePermitirAlterarValoresUsuarioComSetters() {
        UsuarioEntity usuario = new UsuarioEntity();
        LocalDateTime agora = LocalDateTime.now();

        usuario.setIdUsuario("1");
        usuario.setNome("Maria");
        usuario.setUsername("maria123");
        usuario.setSenha("senha456");
        usuario.setEmail("maria@email.com");
        usuario.setRegras(Role.OWNER);
        usuario.setCEP("98765-432");
        usuario.setNumero(50);
        usuario.setActived(false);
        usuario.setCreatedAt(agora);
        usuario.setModifiedAt(agora.plusDays(1));

        assertEquals("1", usuario.getIdUsuario());
        assertEquals("Maria", usuario.getNome());
        assertEquals("maria123", usuario.getUsername());
        assertEquals("senha456", usuario.getSenha());
        assertEquals("maria@email.com", usuario.getEmail());
        assertEquals(Role.OWNER, usuario.getRegras());
        assertEquals("98765-432", usuario.getCEP());
        assertEquals(50, usuario.getNumero());
        assertFalse(usuario.isActived());
        assertEquals(agora, usuario.getCreatedAt());
        assertEquals(agora.plusDays(1), usuario.getModifiedAt());
    }

    @Test
    void deveRetornarRoleOwnerQuandoUsuarioForOwner() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setRegras(Role.OWNER);

        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();

        assertEquals(1, authorities.size());
        assertTrue(
                authorities.stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_OWNER"))
        );
    }

    @Test
    void deveRetornarRoleClientQuandoUsuarioForClient() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setRegras(Role.CLIENT);

        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();

        assertEquals(1, authorities.size());
        assertTrue(
                authorities.stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_CLIENT"))
        );
    }

    @Test
    void deveImplementarUserDetailsCorretamente() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setSenha("senhaSegura");

        assertEquals("senhaSegura", usuario.getPassword());
        assertTrue(usuario.isAccountNonExpired());
        assertTrue(usuario.isAccountNonLocked());
        assertTrue(usuario.isCredentialsNonExpired());
        assertTrue(usuario.isEnabled());
    }

    @Test
    void deveCriarUsuarioComConstrutorVazio() {
        UsuarioEntity usuario = new UsuarioEntity();

        assertNotNull(usuario);
    }

    @Test
    void deveCriarAddressEntityUsandoConstrutorCompleto() {
        AddressEntity address = new AddressEntity(
                "12345-678",
                "Rua das Flores",
                "Centro",
                "São Paulo"
        );

        assertEquals("12345-678", address.getCEP());
        assertEquals("Rua das Flores", address.getLogradouro());
        assertEquals("Centro", address.getBairro());
        assertEquals("São Paulo", address.getCidade());
    }

    @Test
    void deveCriarAddressEntityComConstrutorVazio() {
        AddressEntity address = new AddressEntity();

        assertNotNull(address);
    }

    @Test
    void devePermitirAlterarValoresAddressComSetters() {
        AddressEntity address = new AddressEntity();

        address.setCEP("98765-432");
        address.setLogradouro("Av. Paulista");
        address.setBairro("Bela Vista");
        address.setCidade("São Paulo");

        assertEquals("98765-432", address.getCEP());
        assertEquals("Av. Paulista", address.getLogradouro());
        assertEquals("Bela Vista", address.getBairro());
        assertEquals("São Paulo", address.getCidade());
    }
}
