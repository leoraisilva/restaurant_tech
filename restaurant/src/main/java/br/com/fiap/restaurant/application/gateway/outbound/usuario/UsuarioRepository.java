package br.com.fiap.restaurant.application.gateway.outbound.usuario;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;


public interface UsuarioRepository {
    Usuario change(Usuario usuario);
    Usuario create(Usuario usuario);
    Usuario findByUsername(String username);
    Usuario update(Usuario usuario);
    Usuario delete(Usuario usuario);
    Pagination<Usuario> findAll(Page page);
}
