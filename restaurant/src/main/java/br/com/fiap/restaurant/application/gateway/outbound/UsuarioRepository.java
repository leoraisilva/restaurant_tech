package br.com.fiap.restaurant.application.gateway.outbound;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
    Usuario create(Usuario usuario);
    Usuario findByUsername(String username);
    Usuario update(Usuario usuario);
    Pagination<Usuario> findAll(Page page);
}
