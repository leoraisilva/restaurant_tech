package br.com.fiap.restaurant.application.useCase.inbound.usuario.delete;

import br.com.fiap.restaurant.application.useCase.inbound.usuario.UsuarioPorts;

public class DeleteUsuario {
    private final UsuarioPorts usuarioPorts;

    public DeleteUsuario(UsuarioPorts usuarioPorts) {
        this.usuarioPorts = usuarioPorts;
    }

    public DeleteUsuarioOutput deleteUsuario(String username) {
        return usuarioPorts.deleteUsuario(username);
    }

}
