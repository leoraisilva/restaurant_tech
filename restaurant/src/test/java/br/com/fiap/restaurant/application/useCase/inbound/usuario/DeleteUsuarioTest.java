package br.com.fiap.restaurant.application.useCase.inbound.usuario;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.delete.DeleteUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.delete.DeleteUsuarioOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteUsuarioTest {
    @Mock
    private UsuarioPorts usuarioPorts;

    @InjectMocks
    private DeleteUsuario deleteUsuario;

    @Test
    void deveCriarDeleteUsuarioOutputAPartirDeUsuario () {
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

        DeleteUsuarioOutput result = DeleteUsuarioOutput.from(output);

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
    void deveCriarUsuarioAPartirDeDeleteUsuarioOutput() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );
        var now = LocalDateTime.now();

        DeleteUsuarioOutput output = new DeleteUsuarioOutput(
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

        Usuario result = DeleteUsuarioOutput.to(output);

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
    void deveDelegarCriacaoParaDeleteUsuarioPorts() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        var now = LocalDateTime.now();


        DeleteUsuarioOutput output = new DeleteUsuarioOutput(
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

        when(usuarioPorts.deleteUsuario("jsilva"))
                .thenReturn(output);

        DeleteUsuarioOutput resultado =
                deleteUsuario.deleteUsuario("jsilva");

        assertEquals(output, resultado);

        verify(usuarioPorts, times(1))
                .deleteUsuario("jsilva");
        verifyNoMoreInteractions(usuarioPorts);
    }
}
