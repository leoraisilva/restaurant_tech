package br.com.fiap.restaurant.application.useCase.inbound.usuario;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUsuarioTest {
    @Mock
    private UsuarioPorts usuarioPorts;

    @InjectMocks
    private CreateUsuario createUsuario;

    @Test
    void deveCriarCreateUsuarioInput () {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        var input = new CreateUsuarioInput("Joao", "jsilva", "j123456", "jsilva@exemplo.com", Role.OWNER, endereco, 15);

        assertNotNull(input);
        assertEquals("Joao", input.nome() );
        assertEquals("jsilva", input.username());
        assertEquals("j123456", input.senha());
        assertEquals("jsilva@exemplo.com", input.email());
        assertEquals(Role.OWNER, input.regras());
        assertEquals(endereco, input.endereco());
        assertEquals(15, input.numero());
    }

    @Test
    void deveCriarCreateUsuarioOutputAPartirDeUsuario() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );
        var now = LocalDateTime.now();

        var output = new Usuario.UsuarioBuilder()
                .withNome("Joao")
                .withUsuario("jsilva")
                .withSenha("j123456")
                .withEmail("jsilva@exemplo.com")
                .withRegras(Role.OWNER)
                .withEndereco(endereco)
                .actived(true)
                .withCreatedAt(now)
                .withModifiedAt(now)
                .withNumero(15)
                .build();

        CreateUsuarioOutput result = CreateUsuarioOutput.from(output);

        assertNotNull(result);
        assertEquals("Joao", result.nome() );
        assertEquals("jsilva", result.username());
        assertEquals("j123456", result.senha());
        assertEquals("jsilva@exemplo.com", result.email());
        assertEquals(Role.OWNER, result.regras());
        assertEquals(endereco, result.endereco());
        assertEquals(now, result.createdAt());
        assertEquals(now, result.modifiedAt());
        assertTrue(result.actived());
        assertEquals(15, result.numero());
    }

    @Test
    void deveCriarUsuarioAPartirDeCreateUsuarioOutput() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );
        var now = LocalDateTime.now();

        CreateUsuarioOutput output = new CreateUsuarioOutput(
                "Joao",
                "jsilva",
                "j123456",
                "jsilva@exemplo.com",
                Role.OWNER,
                endereco,
                15,
                true,
                now,
                now
        );

        Usuario result = CreateUsuarioOutput.to(output);

        assertNotNull(result);
        assertEquals("Joao", result.getNome() );
        assertEquals("jsilva", result.getUsername());
        assertEquals("j123456", result.getSenha());
        assertEquals("jsilva@exemplo.com", result.getEmail());
        assertEquals(Role.OWNER, result.getRegras());
        assertEquals(endereco, result.getEndereco());
        assertEquals(15, result.getNumero());

    }

    @Test
    void deveDelegarCriacaoParaCreateUsuarioPorts() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        var now = LocalDateTime.now();
        var input = new CreateUsuarioInput("Joao", "jsilva", "j123456", "jsilva@exemplo.com", Role.OWNER, endereco, 15);


        CreateUsuarioOutput output = new CreateUsuarioOutput(
                "Joao",
                "jsilva",
                "j123456",
                "jsilva@exemplo.com",
                Role.OWNER,
                endereco,
                15,
                true,
                now,
                now
        );

        when(usuarioPorts.createUsuario(input))
                .thenReturn(output);

        CreateUsuarioOutput resultado =
                createUsuario.createUsuario(input);

        assertEquals(output, resultado);

        verify(usuarioPorts, times(1))
                .createUsuario(input);
        verifyNoMoreInteractions(usuarioPorts);
    }

}
