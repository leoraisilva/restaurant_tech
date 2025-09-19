package br.com.fiap.restaurant.application.gateway.inbound.usuario.update;

import br.com.fiap.restaurant.application.domain.usuario.Address;

public record UpdateUsuarioInput(String nome, String usuario, String senha, String email, Address endereco, boolean actived) {
}
