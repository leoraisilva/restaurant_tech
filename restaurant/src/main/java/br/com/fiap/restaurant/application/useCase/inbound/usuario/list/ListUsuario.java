package br.com.fiap.restaurant.application.useCase.inbound.usuario.list;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.UsuarioPorts;

public class ListUsuario {
    private final UsuarioPorts usuarioPorts;

    public ListUsuario(UsuarioPorts usuarioPorts) {
        this.usuarioPorts = usuarioPorts;
    }

    public Pagination<ListUsuarioOutput> listUsuario (Page page) {
        return usuarioPorts.listUsuario(page);
    }
}
