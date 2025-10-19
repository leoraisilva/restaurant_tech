package br.com.fiap.restaurant.application.useCase.inbound.usuario.update;

import br.com.fiap.restaurant.application.domain.usuario.Address;

public record UpdateUsuarioInput(String nome, String username, String email, Address endereco, Integer numero) {
}
