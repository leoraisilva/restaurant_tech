package br.com.fiap.restaurant.application.gateway.inbound.usuario.list;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.get.GetUsuarioOutput;

import java.time.LocalDateTime;

public record ListUsuarioOutput(String nome,
                                String username,
                                String senha,
                                String email,
                                Role regras,
                                Address endereco,
                                Integer numero,
                                boolean actived,
                                LocalDateTime createdAt,
                                LocalDateTime modifiedAt) {

    public static ListUsuarioOutput from(final Usuario usuario) {
        return new ListUsuarioOutput(
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

    public static Usuario to(final ListUsuarioOutput listUsuarioOutput) {
        return new Usuario.UsuarioBuilder()
                .withNome(listUsuarioOutput.nome())
                .withUsuario(listUsuarioOutput.username())
                .withSenha(listUsuarioOutput.senha())
                .withEmail(listUsuarioOutput.email())
                .withRegras(listUsuarioOutput.regras())
                .withEndereco(listUsuarioOutput.endereco())
                .withNumero(listUsuarioOutput.numero())
                .actived(listUsuarioOutput.actived())
                .withModifiedAt(listUsuarioOutput.modifiedAt())
                .build();
    }

}
