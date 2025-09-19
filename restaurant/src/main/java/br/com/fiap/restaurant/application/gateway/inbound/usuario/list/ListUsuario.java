package br.com.fiap.restaurant.application.gateway.inbound.usuario.list;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;

public interface ListUsuario {
    Pagination<ListUsuarioOutput> listUsuario (Page page);
}
