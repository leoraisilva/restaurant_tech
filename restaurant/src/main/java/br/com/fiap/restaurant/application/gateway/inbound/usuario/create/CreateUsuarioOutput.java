package br.com.fiap.restaurant.application.gateway.inbound.usuario.create;

import br.com.fiap.restaurant.application.domain.usuario.*;
import java.time.LocalDateTime;

public record CreateUsuarioOutput(String nome,
                                  String username,
                                  String senha,
                                  String email,
                                  Role regras,
                                  Address endereco,
                                  Integer numero,
                                  boolean actived,
                                  LocalDateTime createdAt,
                                  LocalDateTime modifiedAt) {
    public static CreateUsuarioOutput from(final Usuario usuario) {
        return new CreateUsuarioOutput(
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

    public static Usuario to(final CreateUsuarioOutput createUsuarioOutput) {
        return new Usuario.UsuarioBuilder()
                .withNome(createUsuarioOutput.nome())
                .withUsuario(createUsuarioOutput.username())
                .withSenha(createUsuarioOutput.senha())
                .withEmail(createUsuarioOutput.email())
                .withRegras(createUsuarioOutput.regras())
                .withEndereco(createUsuarioOutput.endereco())
                .withNumero(createUsuarioOutput.numero())
                .actived(createUsuarioOutput.actived())
                .withModifiedAt(createUsuarioOutput.modifiedAt())
                .build();
    }
}
