package br.com.fiap.restaurant.application.useCase.inbound.usuario.create;

import br.com.fiap.restaurant.application.useCase.inbound.usuario.UsuarioPorts;

public class CreateUsuario {
    private final UsuarioPorts usuarioPorts;

    public CreateUsuario(UsuarioPorts usuarioPorts) {
        this.usuarioPorts = usuarioPorts;
    }

    public CreateUsuarioOutput createUsuario(CreateUsuarioInput usuarioInput) {
        return usuarioPorts.createUsuario(usuarioInput);
    }
}
