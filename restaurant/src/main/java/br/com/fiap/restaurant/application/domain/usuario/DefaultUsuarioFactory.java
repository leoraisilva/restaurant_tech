package br.com.fiap.restaurant.application.domain.usuario;

import java.time.LocalDateTime;
import java.util.Objects;

public class DefaultUsuarioFactory implements UsuarioFactory{
    @Override
    public Usuario newUsuario(String nome, String usuario, String senha, String email, Role regras, Address endereco) {
        Objects.requireNonNull(nome, "Nome Obrigatorio");
        Objects.requireNonNull(usuario, "Usuario Obrigatorio");
        Objects.requireNonNull(email, "E-mail Obrigatorio");
        return new Usuario.UsuarioBuilder()
                .withNome(nome)
                .withUsuario(usuario)
                .withEmail(email)
                .withRegras(regras)
                .withEndereco(endereco)
                .actived(true)
                .withModifiedAt(LocalDateTime.now())
                .build();
    }

}
