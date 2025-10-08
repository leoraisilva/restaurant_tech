package br.com.fiap.restaurant.application.useCase.inbound.usuario.update;

import br.com.fiap.restaurant.application.useCase.inbound.usuario.UsuarioPorts;

public class UpdateUsuario {
    private final UsuarioPorts usuarioPorts;

    public UpdateUsuario(UsuarioPorts usuarioPorts) {
        this.usuarioPorts = usuarioPorts;
    }

    public UpdateUsuarioOutput updateUsuario(UpdateUsuarioInput input) {
        return usuarioPorts.updateUsuario(input);
    }
}
