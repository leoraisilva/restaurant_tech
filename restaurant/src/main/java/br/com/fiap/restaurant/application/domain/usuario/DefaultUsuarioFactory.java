package br.com.fiap.restaurant.application.domain.usuario;

import java.time.LocalDateTime;
import java.util.Objects;

public class DefaultUsuarioFactory implements UsuarioFactory{

    public DefaultUsuarioFactory(){}

    @Override
    public Usuario newUsuario(String nome, String username, String senha, String email, Role regras, Address endereco, Integer numero) {
        Objects.requireNonNull(nome, "Nome Obrigatorio");
        Objects.requireNonNull(username, "Usuario Obrigatorio");
        Objects.requireNonNull(email, "E-mail Obrigatorio");
        return new Usuario.UsuarioBuilder()
                .withNome(nome)
                .withUsuario(username)
                .withSenha(senha)
                .withEmail(email)
                .withRegras(regras)
                .withEndereco(endereco)
                .withNumero(numero)
                .actived(true)
                .withModifiedAt(LocalDateTime.now())
                .build();
    }

}
