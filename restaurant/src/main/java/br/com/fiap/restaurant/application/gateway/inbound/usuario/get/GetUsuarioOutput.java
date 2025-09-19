package br.com.fiap.restaurant.application.gateway.inbound.usuario.get;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;

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
}
