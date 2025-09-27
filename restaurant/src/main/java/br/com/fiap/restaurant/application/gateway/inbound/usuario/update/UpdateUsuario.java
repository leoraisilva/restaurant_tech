package br.com.fiap.restaurant.application.gateway.inbound.usuario.update;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.UsuarioPorts;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.list.ListUsuarioOutput;

public class UpdateUsuario {
    private final UsuarioPorts usuarioPorts;

    public UpdateUsuario(UsuarioPorts usuarioPorts) {
        this.usuarioPorts = usuarioPorts;
    }

    public UpdateUsuarioOutput updateUsuario(UpdateUsuarioInput input) {
        return usuarioPorts.updateUsuario(input);
    }
}
