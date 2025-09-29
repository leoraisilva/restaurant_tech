package br.com.fiap.restaurant.application.services;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.usuario.*;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.UsuarioPorts;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.change.ChangeUsuarioInput;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.change.ChangeUsuarioOutput;
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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

public class UsuarioService implements UsuarioPorts {

    final private UsuarioRepository repository;
    final private UsuarioFactory factory;

    public UsuarioService(UsuarioRepository repository, UsuarioFactory factory) {
        Objects.requireNonNull(repository);
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public CreateUsuarioOutput createUsuario(CreateUsuarioInput usuarioInput) {
        var input = factory.newUsuario(usuarioInput.nome(), usuarioInput.username(), usuarioInput.senha(), usuarioInput.email(), usuarioInput.regras(), usuarioInput.endereco(), usuarioInput.numero());
        var usuario = repository.create(input);
        return CreateUsuarioOutput.from(usuario);
    }

    @Override
    public UpdateUsuarioOutput updateUsuario(UpdateUsuarioInput input) {
        var usuario = repository.findByUsername(input.username());
        usuario.update(input.nome(), input.username(), input.email(), input.endereco(), input.numero());
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
        return DeleteUsuarioOutput.from(repository.delete(usuario));
    }

    @Override
    public Pagination<ListUsuarioOutput> listUsuario(Page page) {
        return repository.findAll(page).mapItems(ListUsuarioOutput::from);
    }

    @Override
    public ChangeUsuarioOutput changeUsuario(ChangeUsuarioInput input) {
        var usuario = repository.findByUsername(input.username());
        usuario.updatePassword(input.senha());
        return ChangeUsuarioOutput.from(repository.change(usuario));
    }
}
