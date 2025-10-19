package br.com.fiap.restaurant.application.useCase.inbound.usuario.get;

import br.com.fiap.restaurant.application.useCase.inbound.usuario.UsuarioPorts;

public class GetUsuario {
    private final UsuarioPorts usuarioPorts;

    public GetUsuario(UsuarioPorts usuarioPorts) {
        this.usuarioPorts = usuarioPorts;
    }

    public GetUsuarioOutput getUsuario (String username) {
        return usuarioPorts.getUsuario(username);
    }
}
