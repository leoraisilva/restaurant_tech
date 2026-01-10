package br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.inbound;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantInput;

import java.time.LocalDateTime;

public record UpdateDTO (String nomeRestaurante, String tipo, Address endereco, LocalDateTime abertura, LocalDateTime fechamento) {
    public static UpdateRestaurantInput from(UpdateDTO updateDTO) {
        return new UpdateRestaurantInput(
                updateDTO.nomeRestaurante(),
                updateDTO.tipo(),
                updateDTO.endereco(),
                updateDTO.abertura(),
                updateDTO.fechamento()
        );
    }
}
