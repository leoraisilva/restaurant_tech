package br.com.fiap.restaurant.application.gateway.inbound.delete;

import br.com.fiap.restaurant.application.domain.usuario.Usuario;

public interface DeleteUsuario {
    DeleteUsuarioOutput deleteUsuario(String username);
}
