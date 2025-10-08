package br.com.fiap.restaurant.application.useCase.inbound.usuario;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.change.ChangeUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.change.ChangeUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.delete.DeleteUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.get.GetUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.list.ListUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.update.UpdateUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.update.UpdateUsuarioOutput;

public interface UsuarioPorts {
    ChangeUsuarioOutput changeUsuario(ChangeUsuarioInput input);
    UpdateUsuarioOutput updateUsuario(UpdateUsuarioInput input);
    CreateUsuarioOutput createUsuario (CreateUsuarioInput input);
    GetUsuarioOutput getUsuario (String username);
    Pagination<ListUsuarioOutput> listUsuario (Page page);
    DeleteUsuarioOutput deleteUsuario(String username);

}
