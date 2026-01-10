package br.com.fiap.restaurant.application.useCase.inbound.usuario;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.CardapioPorts;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.list.ListCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.update.UpdateUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.update.UpdateUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.update.UpdateUsuarioOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateUsuarioTest {
    @Mock
    private UsuarioPorts usuarioPorts;

    @InjectMocks
    private UpdateUsuario updateUsuario;

    @Test
    void deveCriarUpdateUsuarioInput () {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        var input = new UpdateUsuarioInput("Joao", "jsilva", "jsilva@exemplo.com",  endereco, 15);

        assertNotNull(input);
        assertEquals("Joao", input.nome() );
        assertEquals("jsilva", input.username());
        assertEquals("jsilva@exemplo.com", input.email());
        assertEquals(endereco, input.endereco());
        assertEquals(15, input.numero());
    }

    @Test
    void deveCriarUpdateUsuarioOutputAPartirDeUsuario() {
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

        UpdateUsuarioOutput result = UpdateUsuarioOutput.from(output);

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
    void deveCriarUsuarioAPartirDeUpdateUsuarioOutput() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );
        var now = LocalDateTime.now();

        UpdateUsuarioOutput output = new UpdateUsuarioOutput(
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

        Usuario result = UpdateUsuarioOutput.to(output);

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
    void deveDelegarCriacaoParaUpdateUsuarioPorts() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        var now = LocalDateTime.now();
        var input = new UpdateUsuarioInput("Joao", "jsilva", "j123456", endereco, 15);


        UpdateUsuarioOutput output = new UpdateUsuarioOutput(
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

        when(usuarioPorts.updateUsuario(input))
                .thenReturn(output);

        UpdateUsuarioOutput resultado =
                updateUsuario.updateUsuario(input);

        assertEquals(output, resultado);

        verify(usuarioPorts, times(1))
                .updateUsuario(input);
        verifyNoMoreInteractions(usuarioPorts);
    }

}
