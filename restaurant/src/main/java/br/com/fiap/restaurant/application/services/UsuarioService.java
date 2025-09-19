package br.com.fiap.restaurant.application.services;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.usuario.*;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.create.CreateUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.create.CreateUsuarioInput;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.create.CreateUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.delete.DeleteUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.delete.DeleteUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.get.GetUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.get.GetUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.list.ListUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.list.ListUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.update.UpdateUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.update.UpdateUsuarioInput;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.update.UpdateUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.outbound.usuario.UsuarioRepository;

import java.util.Objects;

public class UsuarioService implements CreateUsuario, UpdateUsuario, GetUsuario, DeleteUsuario, ListUsuario {

    final private UsuarioRepository repository;
    final private UsuarioFactory factory;

    public UsuarioService(UsuarioRepository repository, UsuarioFactory factory) {
        Objects.requireNonNull(repository);
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public CreateUsuarioOutput createUsuario(CreateUsuarioInput usuarioInput) {
        var input = factory.newUsuario(usuarioInput.nome(), usuarioInput.usuario(), usuarioInput.senha(), usuarioInput.email(), Role.CLIENT, null);
        var usuario = repository.create(input);
        return CreateUsuarioOutput.from(usuario);
    }

    @Override
    public UpdateUsuarioOutput updateUsuario(UpdateUsuarioInput input) {
        var usuario = repository.findByUsername(input.usuario());
        usuario.update(input.nome(), input.email(), input.endereco(),input.actived());
        repository.update(usuario);
        return UpdateUsuarioOutput.from(usuario);
    }

    @Override
    public GetUsuarioOutput getUsuario(String username) {
        var usuario = repository.findByUsername(username);
        return GetUsuarioOutput.from(usuario);
    }

    @Override
    public DeleteUsuarioOutput deleteUsuario(String username) {
        var usuario = repository.findByUsername(username);
        return DeleteUsuarioOutput.from(usuario.delete(usuario.isActived()));
    }

    @Override
    public Pagination<ListUsuarioOutput> listUsuario(Page page) {
        return repository.findAll(page).mapItems(ListUsuarioOutput::from);
    }
}
