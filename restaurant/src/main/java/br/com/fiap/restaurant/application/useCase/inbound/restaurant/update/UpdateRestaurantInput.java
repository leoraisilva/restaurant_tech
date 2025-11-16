package br.com.fiap.restaurant.application.useCase.inbound.restaurant.update;

import br.com.fiap.restaurant.application.domain.usuario.Address;

import java.time.LocalDateTime;

public record UpdateRestaurantInput (String nomeRestaurante, String tipo, Address endereco, LocalDateTime abertura, LocalDateTime fechamento) {
}
