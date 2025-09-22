package br.com.fiap.restaurant.application.gateway.inbound.usuario.create;

import br.com.fiap.restaurant.application.domain.usuario.*;
import java.time.LocalDateTime;

public record CreateUsuarioOutput(String nome,
                                  String usuario,
                                  String senha,
                                  String email,
                                  Role regras,
                                  Address endereco,
                                  boolean actived,
                                  LocalDateTime createdAt,
                                  LocalDateTime modifiedAt) {
    public static CreateUsuarioOutput from(final Usuario usuario) {
        return new CreateUsuarioOutput(
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

    public static Usuario to(final CreateUsuarioOutput createUsuarioOutput) {
        return new Usuario.UsuarioBuilder()
                .withNome(createUsuarioOutput.nome())
                .withUsuario(createUsuarioOutput.usuario())
                .withSenha(createUsuarioOutput.senha())
                .withEmail(createUsuarioOutput.email())
                .withRegras(createUsuarioOutput.regras())
                .withEndereco(createUsuarioOutput.endereco())
                .actived(createUsuarioOutput.actived())
                .withModifiedAt(createUsuarioOutput.modifiedAt())
                .build();
    }
}
