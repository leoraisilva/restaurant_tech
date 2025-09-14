package br.com.fiap.restaurant.application.gateway.inbound.get;

import br.com.fiap.restaurant.application.domain.usuario.Usuario;

public interface GetUsuario {
    GetUsuarioOutput getUsuario (String username);
}
