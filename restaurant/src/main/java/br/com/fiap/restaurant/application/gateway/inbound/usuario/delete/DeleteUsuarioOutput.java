package br.com.fiap.restaurant.application.gateway.inbound.usuario.delete;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.create.CreateUsuarioOutput;

import java.time.LocalDateTime;

public record DeleteUsuarioOutput (String nome,
                                   String username,
                                   String senha,
                                   String email,
                                   Role regras,
                                   Address endereco,
                                   Integer numero,
                                   boolean actived,
                                   LocalDateTime createdAt,
                                   LocalDateTime modifiedAt) {
    public static DeleteUsuarioOutput from(final Usuario usuario) {
        return new DeleteUsuarioOutput(
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

    public static Usuario to(final DeleteUsuarioOutput deleteUsuarioOutput) {
        return new Usuario.UsuarioBuilder()
                .withNome(deleteUsuarioOutput.nome())
                .withUsuario(deleteUsuarioOutput.username())
                .withSenha(deleteUsuarioOutput.senha())
                .withEmail(deleteUsuarioOutput.email())
                .withRegras(deleteUsuarioOutput.regras())
                .withEndereco(deleteUsuarioOutput.endereco())
                .withNumero(deleteUsuarioOutput.numero())
                .actived(deleteUsuarioOutput.actived())
                .withModifiedAt(deleteUsuarioOutput.modifiedAt())
                .build();
    }

}
