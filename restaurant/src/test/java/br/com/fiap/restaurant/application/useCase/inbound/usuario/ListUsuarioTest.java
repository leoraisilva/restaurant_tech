package br.com.fiap.restaurant.application.useCase.inbound.usuario;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.list.ListCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.list.ListCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.list.ListUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.list.ListUsuarioOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListUsuarioTest {
    @Mock
    private UsuarioPorts usuarioPorts;

    @InjectMocks
    private ListUsuario listUsuario;

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

        ListUsuarioOutput result = ListUsuarioOutput.from(output);

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

        ListUsuarioOutput output = new ListUsuarioOutput(
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

        Usuario result = ListUsuarioOutput.to(output);

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
    void deveDelegarListagemParaUsuarioPorts() {
        ListUsuario listUsuario =
                new ListUsuario(usuarioPorts);

        Page page = mock(Page.class);
        Pagination<ListUsuarioOutput> paginacao =
                mock(Pagination.class);

        when(usuarioPorts.listUsuario(page))
                .thenReturn(paginacao);

        Pagination<ListUsuarioOutput> resultado =
                listUsuario.listUsuario(page);

        assertEquals(paginacao, resultado);
        verify(usuarioPorts, times(1))
                .listUsuario(page);
        verifyNoMoreInteractions(usuarioPorts);
    }

}
