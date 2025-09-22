package br.com.fiap.restaurant.application.gateway.inbound.usuario.get;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.update.UpdateUsuarioOutput;

import java.time.LocalDateTime;

public record GetUsuarioOutput (String nome,
                                String usuario,
                                String senha,
                                String email,
                                Role regras,
                                Address endereco,
                                boolean actived,
                                LocalDateTime createdAt,
                                LocalDateTime modifiedAt) {
    public static GetUsuarioOutput from(final Usuario usuario) {
        if (!usuario.isActived()){
            throw new IllegalArgumentException("Nao é Possivel acessar o usuario");
        }
        return new GetUsuarioOutput(usuario.getNome(),
                usuario.getUsuario(),
                usuario.getSenha(),
                usuario.getEmail(),
                usuario.getRegras(),
                usuario.getEndereco(),
                usuario.isActived(),
                usuario.getCreatedAt(),
                usuario.getModifiedAt());
    }

    public static Usuario to(GetUsuarioOutput getUsuarioOutput) {
        return new Usuario.UsuarioBuilder()
                .withNome(getUsuarioOutput.nome())
                .withUsuario(getUsuarioOutput.usuario())
                .withSenha(getUsuarioOutput.senha())
                .withEmail(getUsuarioOutput.email())
                .withRegras(getUsuarioOutput.regras())
                .withEndereco(getUsuarioOutput.endereco())
                .actived(getUsuarioOutput.actived())
                .withModifiedAt(getUsuarioOutput.modifiedAt())
                .build();
    }
}
