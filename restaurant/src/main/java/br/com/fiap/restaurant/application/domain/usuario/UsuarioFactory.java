package br.com.fiap.restaurant.application.domain.usuario;

import java.time.LocalDateTime;

public interface UsuarioFactory {
    Usuario newUsuario(String nome, String usuario, String senha, String email, Role regras, Address endereco, Integer numero);
}
