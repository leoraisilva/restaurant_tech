package br.com.fiap.restaurant.application.gateway.inbound.usuario.delete;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DeleteUsuarioOutput (String nome,
                                   String usuario,
                                   String senha,
                                   String email,
                                   Role regras,
                                   Address endereco,
                                   boolean actived,
                                   LocalDateTime createdAt,
                                   LocalDateTime modifiedAt) {
    public static DeleteUsuarioOutput from(final Usuario usuario) {
        return new DeleteUsuarioOutput(
                usuario.getNome(),
                usuario.getUsuario(),
                usuario.getSenha(),
                usuario.getEmail(),
                usuario.getRegras(),
                usuario.getEndereco(),
                usuario.isActived(),
                usuario.getCreatedAt(),
                usuario.getModifiedAt()
        );
    }
}
