package br.com.fiap.restaurant.application.gateway.inbound.usuario.list;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record ListUsuarioOutput(String nome,
                                String usuario,
                                String senha,
                                String email,
                                Role regras,
                                Address endereco,
                                boolean actived,
                                LocalDateTime createdAt,
                                LocalDateTime modifiedAt) {
    public static ListUsuarioOutput from(Usuario usuario) {
        return new ListUsuarioOutput(usuario.getNome(),
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
