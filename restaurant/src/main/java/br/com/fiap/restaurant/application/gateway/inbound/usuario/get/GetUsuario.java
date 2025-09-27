package br.com.fiap.restaurant.application.gateway.inbound.usuario.get;

import br.com.fiap.restaurant.application.gateway.inbound.usuario.UsuarioPorts;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.delete.DeleteUsuarioOutput;

public class GetUsuario {
    private final UsuarioPorts usuarioPorts;

    public GetUsuario(UsuarioPorts usuarioPorts) {
        this.usuarioPorts = usuarioPorts;
    }

    public GetUsuarioOutput getUsuario (String username) {
        return usuarioPorts.getUsuario(username);
    }
}
