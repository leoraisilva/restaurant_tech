package br.com.fiap.restaurant.application.gateway.inbound.usuario.update;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record UpdateUsuarioOutput (String nome,
                                   String usuario,
                                   String senha,
                                   String email,
                                   Role regras,
                                   Address endereco,
                                   boolean actived,
                                   LocalDateTime createdAt,
                                   LocalDateTime modifiedAt) {
    public static UpdateUsuarioOutput from(final Usuario usuario) {
        if (!usuario.isActived()){
            throw new IllegalArgumentException("Nao é Possivel acessar o usuario");
        }
        return new UpdateUsuarioOutput(
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

    public static Usuario to(final UpdateUsuarioOutput updateUsuarioOutput){
        return new Usuario.UsuarioBuilder()
                .withNome(updateUsuarioOutput.nome())
                .withUsuario(updateUsuarioOutput.usuario())
                .withSenha(updateUsuarioOutput.senha())
                .withEmail(updateUsuarioOutput.email())
                .withRegras(updateUsuarioOutput.regras())
                .withEndereco(updateUsuarioOutput.endereco())
                .actived(updateUsuarioOutput.actived())
                .withModifiedAt(updateUsuarioOutput.modifiedAt())
                .build();
    }
}
