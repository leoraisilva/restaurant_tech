package br.com.fiap.restaurant.application.useCase.inbound.usuario;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.change.ChangeUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.change.ChangeUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.change.ChangeUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.update.UpdateUsuarioInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class ChangeUsuarioTest {
    @Mock
    private UsuarioPorts usuarioPorts;

    @InjectMocks
    private ChangeUsuario changeUsuario;

    @Test
    void deveCriarChangeUsuarioInput () {

        var input = new ChangeUsuarioInput("jsilva", "j123456");

        assertNotNull(input);
        assertEquals("jsilva", input.username());
        assertEquals("j123456", input.senha());
    }

    @Test
    void deveCriarChangeUsuarioOutputAPartirDeUsuario() {
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

        ChangeUsuarioOutput result = ChangeUsuarioOutput.from(output);

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
    void deveCriarUsuarioAPartirDeChangeUsuarioOutput() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );
        var now = LocalDateTime.now();

        ChangeUsuarioOutput output = new ChangeUsuarioOutput(
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

        Usuario result = ChangeUsuarioOutput.to(output);

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
        var input = new ChangeUsuarioInput("jsilva", "j123456");


        ChangeUsuarioOutput output = new ChangeUsuarioOutput(
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

        when(usuarioPorts.changeUsuario(input))
                .thenReturn(output);

        ChangeUsuarioOutput resultado =
                changeUsuario.changeUsuario(input);

        assertEquals(output, resultado);

        verify(usuarioPorts, times(1))
                .changeUsuario(input);
        verifyNoMoreInteractions(usuarioPorts);
    }
}
