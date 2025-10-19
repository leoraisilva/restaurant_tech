package br.com.fiap.restaurant.application.useCase.inbound.usuario.create;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;

public record CreateUsuarioInput(String nome, String username, String senha, String email, Role regras, Address endereco, Integer numero) {
}
