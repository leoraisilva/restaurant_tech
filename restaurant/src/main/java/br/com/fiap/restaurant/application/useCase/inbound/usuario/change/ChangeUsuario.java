package br.com.fiap.restaurant.application.useCase.inbound.usuario.change;

import br.com.fiap.restaurant.application.useCase.inbound.usuario.UsuarioPorts;

public class ChangeUsuario {
    private final UsuarioPorts usuarioPorts;

    public ChangeUsuario(UsuarioPorts usuarioPorts) {
        this.usuarioPorts = usuarioPorts;
    }

    public ChangeUsuarioOutput changeUsuario (ChangeUsuarioInput input) {
        return usuarioPorts.changeUsuario(input);
    }
}
