package br.com.fiap.restaurant.application.useCase.inbound.usuario.update;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record UpdateUsuarioOutput (String nome,
                                   String username,
                                   String senha,
                                   String email,
                                   Role regras,
                                   Address endereco,
                                   Integer numero,
                                   boolean actived,
                                   LocalDateTime createdAt,
                                   LocalDateTime modifiedAt) {

    public static UpdateUsuarioOutput from(final Usuario usuario) {
        return new UpdateUsuarioOutput(
                usuario.getNome(),
                usuario.getUsername(),
                usuario.getSenha(),
                usuario.getEmail(),
                usuario.getRegras(),
                usuario.getEndereco(),
                usuario.getNumero(),
                usuario.isActived(),
                usuario.getCreatedAt(),
                usuario.getModifiedAt()
        );
    }

    public static Usuario to(final UpdateUsuarioOutput updateUsuarioOutput) {
        return new Usuario.UsuarioBuilder()
                .withNome(updateUsuarioOutput.nome())
                .withUsuario(updateUsuarioOutput.username())
                .withSenha(updateUsuarioOutput.senha())
                .withEmail(updateUsuarioOutput.email())
                .withRegras(updateUsuarioOutput.regras())
                .withEndereco(updateUsuarioOutput.endereco())
                .withNumero(updateUsuarioOutput.numero())
                .actived(updateUsuarioOutput.actived())
                .withModifiedAt(updateUsuarioOutput.modifiedAt())
                .build();
    }
}
