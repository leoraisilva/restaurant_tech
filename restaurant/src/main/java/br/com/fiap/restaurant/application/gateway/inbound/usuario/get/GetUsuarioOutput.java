package br.com.fiap.restaurant.application.gateway.inbound.usuario.get;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.delete.DeleteUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.update.UpdateUsuarioOutput;

import java.time.LocalDateTime;

public record GetUsuarioOutput (String nome,
                                String username,
                                String senha,
                                String email,
                                Role regras,
                                Address endereco,
                                Integer numero,
                                boolean actived,
                                LocalDateTime createdAt,
                                LocalDateTime modifiedAt) {

    public static GetUsuarioOutput from(final Usuario usuario) {
        return new GetUsuarioOutput(
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

    public static Usuario to(final GetUsuarioOutput getUsuarioOutput) {
        return new Usuario.UsuarioBuilder()
                .withNome(getUsuarioOutput.nome())
                .withUsuario(getUsuarioOutput.username())
                .withSenha(getUsuarioOutput.senha())
                .withEmail(getUsuarioOutput.email())
                .withRegras(getUsuarioOutput.regras())
                .withEndereco(getUsuarioOutput.endereco())
                .withNumero(getUsuarioOutput.numero())
                .actived(getUsuarioOutput.actived())
                .withModifiedAt(getUsuarioOutput.modifiedAt())
                .build();
    }
}
