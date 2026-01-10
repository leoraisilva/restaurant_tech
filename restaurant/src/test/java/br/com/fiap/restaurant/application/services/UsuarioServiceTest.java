package br.com.fiap.restaurant.application.services;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.domain.usuario.UsuarioFactory;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.change.ChangeUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.update.UpdateUsuarioInput;
import br.com.fiap.restaurant.application.useCase.outbound.usuario.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @Mock
    private UsuarioRepository repository;

    @Mock
    private UsuarioFactory factory;

    @InjectMocks
    private UsuarioService service;

    private Usuario usuario;

    @BeforeEach
    void setup() {
        usuario = mock(Usuario.class);
    }

    @Test
    void deveCriarUsuarioComSucesso() {
        CreateUsuarioInput input = mock(CreateUsuarioInput.class);

        when(factory.newUsuario(
                any(), any(), any(), any(), any(), any(), any()
        )).thenReturn(usuario);

        when(repository.create(usuario)).thenReturn(usuario);

        var output = service.createUsuario(input);

        assertNotNull(output);
        verify(factory).newUsuario(
                any(), any(), any(), any(), any(), any(), any()
        );
        verify(repository).create(usuario);
    }

    @Test
    void deveAtualizarUsuarioComSucesso() {
        UpdateUsuarioInput input = mock(UpdateUsuarioInput.class);

        when(input.username()).thenReturn("john");
        when(repository.findByUsername("john")).thenReturn(usuario);

        var output = service.updateUsuario(input);

        assertNotNull(output);
        verify(usuario).update(
                any(), any(), any(), any(), any()
        );
        verify(repository).update(usuario);
    }

    @Test
    void deveBuscarUsuarioPorUsername() {
        when(repository.findByUsername("john")).thenReturn(usuario);

        var output = service.getUsuario("john");

        assertNotNull(output);
        verify(repository).findByUsername("john");
    }

    @Test
    void deveDeletarUsuarioComSucesso() {
        when(repository.findByUsername("john")).thenReturn(usuario);
        when(repository.delete(usuario)).thenReturn(usuario);

        var output = service.deleteUsuario("john");

        assertNotNull(output);
        verify(repository).delete(usuario);
    }

    @Test
    void deveListarUsuariosComPaginacao() {
        Page page = mock(Page.class);
        Pagination<Usuario> pagination = mock(Pagination.class);

        when(repository.findAll(page)).thenReturn(pagination);
        when(pagination.mapItems(any())).thenReturn(mock(Pagination.class));

        var result = service.listUsuario(page);

        assertNotNull(result);
        verify(repository).findAll(page);
    }

    @Test
    void deveAlterarSenhaDoUsuario() {
        ChangeUsuarioInput input = mock(ChangeUsuarioInput.class);

        when(input.username()).thenReturn("john");
        when(repository.findByUsername("john")).thenReturn(usuario);
        when(repository.change(usuario)).thenReturn(usuario);

        var output = service.changeUsuario(input);

        assertNotNull(output);
        verify(usuario).updatePassword(any());
        verify(repository).change(usuario);
    }
}
