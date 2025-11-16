package br.com.fiap.restaurant.application.useCase.inbound.restaurant.create;

import br.com.fiap.restaurant.application.domain.usuario.Address;

import java.time.LocalDateTime;

public record CreateRestaurantInput (String nomeRestaurante, String tipo, Address endereco, LocalDateTime abertura, LocalDateTime fechamento, String responsavel) {
}
