package br.com.fiap.restaurant.application.domain.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {
    private Usuario usuario;
    private Address endereco;
    private Role role;

    @BeforeEach
    void setup() {
        endereco = new Address("13216540", "Rua Alagoas", "Jd. Taruma", "Jundiai");
        role = Role.OWNER;

        usuario = new Usuario.UsuarioBuilder()
                .withNome("João")
                .withUsuario("joao123")
                .withSenha("123456")
                .withEmail("joao@email.com")
                .withEndereco(endereco)
                .withNumero(100)
                .withRegras(role)
                .actived(true)
                .build();
    }

    @Test
    void deveCriarUsuarioComBuilder() {
        assertNotNull(usuario);
        assertEquals("João", usuario.getNome());
        assertEquals("joao123", usuario.getUsername());
        assertEquals("joao@email.com", usuario.getEmail());
        assertTrue(usuario.isActived());
        assertNotNull(usuario.getCreatedAt());
    }

    @Test
    void deveAtualizarDadosDoUsuario() {
        Address novoEndereco = new Address("13216540", "Rua Alagoas", "Jd. Taruma", "Jundiai");

        usuario.update(
                "Maria",
                "maria123",
                "maria@email.com",
                novoEndereco,
                200
        );

        assertEquals("Maria", usuario.getNome());
        assertEquals("maria123", usuario.getUsername());
        assertEquals("maria@email.com", usuario.getEmail());
        assertEquals(Role.OWNER, usuario.getRegras());
        assertEquals(novoEndereco, usuario.getEndereco());
        assertEquals(200, usuario.getNumero());
        assertNotNull(usuario.getModifiedAt());
    }

    @Test
    void deveAtualizarSenhaQuandoUsuarioAtivo() {
        usuario.updatePassword("novaSenha");

        assertEquals("novaSenha", usuario.getSenha());
    }

    @Test
    void naoDeveAtualizarSenhaQuandoUsuarioInativo() {
        usuario.delete(true);
        usuario.updatePassword("novaSenha");

        assertNotEquals("novaSenha", usuario.getSenha());
    }

    @Test
    void deveDesativarUsuario() {
        usuario.delete(true);

        assertFalse(usuario.isActived());
    }

    @Test
    void naoDeveDesativarUsuarioJaInativo() {
        usuario.delete(true);
        usuario.delete(false);

        assertFalse(usuario.isActived());
    }

    @Test
    void deveCriarUsuarioComCreatedAtAutomatico() {
        LocalDateTime data = LocalDateTime.of(2024, 1, 1, 10, 0);
        Usuario usuario = new Usuario.UsuarioBuilder()
                .withNome("João")
                .withUsuario("joao")
                .withSenha("123")
                .withEmail("joao@email.com")
                .withModifiedAt(LocalDateTime.now())
                .actived(true)
                .build();

        assertNotNull(usuario.getCreatedAt());
        assertNotNull(usuario.getModifiedAt());
    }

    @Test
    void deveCriarUsuarioComCreatedAtInformado() {
        LocalDateTime data = LocalDateTime.of(2024, 1, 1, 10, 0);

        Usuario usuario = new Usuario.UsuarioBuilder()
                .withNome("Maria")
                .withUsuario("maria")
                .withSenha("123")
                .withEmail("maria@email.com")
                .withModifiedAt(data)
                .withCreatedAt(data)
                .actived(true)
                .build();

        assertEquals(data, usuario.getCreatedAt());
        assertEquals(data, usuario.getModifiedAt());
    }

    @Test
    void deveCriarEnderecoQuandoCepValido() {
        Address address = new Address(
                "01001000",
                "Praça da Sé",
                "Sé",
                "São Paulo"
        );

        assertNotNull(address);
        assertEquals("01001000", address.CEP());
        assertEquals("Praça da Sé", address.logradouro());
        assertEquals("Sé", address.bairro());
        assertEquals("São Paulo", address.cidade());
    }

    @Test
    void deveLancarExceptionQuandoCepNull() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new Address(
                        null,
                        "Rua A",
                        "Centro",
                        "São Paulo"
                )
        );

        assertEquals("CEP Obrigatorio", exception.getMessage());
    }

    @Test
    void deveLancarExceptionQuandoCepVazio() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Address(
                        "",
                        "Rua A",
                        "Centro",
                        "São Paulo"
                )
        );

        assertEquals("CEP invalido", exception.getMessage());
    }

    @Test
    void deveLancarExceptionQuandoCepInvalido() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Address(
                        "1234",
                        "Rua A",
                        "Centro",
                        "São Paulo"
                )
        );

        assertEquals("CEP invalido", exception.getMessage());
    }

    @Test
    void deveCriarUsuarioFactory() {
        UsuarioFactory factory = new DefaultUsuarioFactory();
        endereco = new Address("13216540", "Rua Alagoas", "Jd. Taruma", "Jundiai");
        role = Role.CLIENT;

        var usuario = factory.newUsuario("João", "joao123", "123456", "joao@email.com", role, endereco, 20);

        assertEquals("João", usuario.getNome());
        assertEquals("joao123", usuario.getUsername());
        assertEquals("123456", usuario.getSenha());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals(Role.CLIENT, usuario.getRegras());
        assertEquals(endereco, usuario.getEndereco());
        assertEquals(20, usuario.getNumero());
    }

    @Test
    void deveCriarUsuarioFactoryNaoNull() {
        UsuarioFactory factory = new DefaultUsuarioFactory();
        endereco = new Address("13216540", "Rua Alagoas", "Jd. Taruma", "Jundiai");
        role = Role.CLIENT;

        var usuario = factory.newUsuario("João", "joao123", "123456", "joao@email.com", role, endereco, 20);

        assertNotNull(usuario.getNome());
        assertNotNull(usuario.getUsername());
        assertNotNull(usuario.getSenha());
        assertNotNull(usuario.getEmail());
        assertNotNull(usuario.getRegras());
        assertNotNull(usuario.getEndereco());
        assertNotNull(usuario.getNumero());
        assertNotNull(usuario.getModifiedAt());
        assertNotNull(usuario.getCreatedAt());
    }

    @Test
    void naoDeveCriarUsuarioFactoryNomeNull() {
        endereco = new Address("13216540", "Rua Alagoas", "Jd. Taruma", "Jundiai");
        role = Role.CLIENT;

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new DefaultUsuarioFactory().newUsuario(null, "joao123", "123456", "joao@email.com", role, endereco, 20)
        );

        assertEquals("Nome Obrigatorio", exception.getMessage());
    }

    @Test
    void naoDeveCriarUsuarioFactoryUsernameNull() {
        endereco = new Address("13216540", "Rua Alagoas", "Jd. Taruma", "Jundiai");
        role = Role.CLIENT;

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new DefaultUsuarioFactory().newUsuario("João", null, "123456", "joao@email.com", role, endereco, 20)
        );

        assertEquals("Usuario Obrigatorio", exception.getMessage());
    }

    @Test
    void naoDeveCriarUsuarioFactoryEmailNull() {
        endereco = new Address("13216540", "Rua Alagoas", "Jd. Taruma", "Jundiai");
        role = Role.CLIENT;

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new DefaultUsuarioFactory().newUsuario("João", "joao123", "123456", null, role, endereco, 20)
        );

        assertEquals("E-mail Obrigatorio", exception.getMessage());
    }

    @Test
    void deveCriarAddressFactory() {
        AddressFactory factory = new DefaultAddressFactory();
        var address =  factory.newAddress("13216540");

        assertEquals("13216540", address.CEP());
    }

}
