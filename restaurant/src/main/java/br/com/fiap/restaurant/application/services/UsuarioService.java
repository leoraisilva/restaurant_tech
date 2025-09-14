package br.com.fiap.restaurant.application.services;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.usuario.*;
import br.com.fiap.restaurant.application.gateway.inbound.create.CreateUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.create.CreateUsuarioInput;
import br.com.fiap.restaurant.application.gateway.inbound.create.CreateUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.inbound.delete.DeleteUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.delete.DeleteUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.inbound.get.GetUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.get.GetUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.inbound.list.ListUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.list.ListUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.inbound.update.UpdateUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.update.UpdateUsuarioInput;
import br.com.fiap.restaurant.application.gateway.inbound.update.UpdateUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.outbound.UsuarioRepository;

import java.util.Objects;
import java.util.function.Function;

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
