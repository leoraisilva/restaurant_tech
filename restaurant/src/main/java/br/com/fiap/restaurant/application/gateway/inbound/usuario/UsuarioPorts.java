package br.com.fiap.restaurant.application.gateway.inbound.usuario;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
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

public interface UsuarioPorts {
    UpdateUsuarioOutput updateUsuario(UpdateUsuarioInput input);
    CreateUsuarioOutput createUsuario (CreateUsuarioInput input);
    GetUsuarioOutput getUsuario (String username);
    Pagination<ListUsuarioOutput> listUsuario (Page page);
    DeleteUsuarioOutput deleteUsuario(String username);

}
