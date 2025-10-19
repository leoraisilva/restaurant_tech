package br.com.fiap.restaurant.application.useCase.inbound.usuario.change;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record ChangeUsuarioOutput (String nome,
                                  String username,
                                  String senha,
                                  String email,
                                  Role regras,
                                  Address endereco,
                                  Integer numero,
                                  boolean actived,
                                  LocalDateTime createdAt,
                                  LocalDateTime modifiedAt){
    public static ChangeUsuarioOutput from(final Usuario usuario) {
        return new ChangeUsuarioOutput(
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

    public static Usuario to(final ChangeUsuarioOutput changeUsuarioOutput) {
        return new Usuario.UsuarioBuilder()
                .withNome(changeUsuarioOutput.nome())
                .withUsuario(changeUsuarioOutput.username())
                .withSenha(changeUsuarioOutput.senha())
                .withEmail(changeUsuarioOutput.email())
                .withRegras(changeUsuarioOutput.regras())
                .withEndereco(changeUsuarioOutput.endereco())
                .withNumero(changeUsuarioOutput.numero())
                .actived(changeUsuarioOutput.actived())
                .withModifiedAt(changeUsuarioOutput.modifiedAt())
                .build();
    }
}
