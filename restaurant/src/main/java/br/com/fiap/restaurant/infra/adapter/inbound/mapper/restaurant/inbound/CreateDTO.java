package br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.inbound;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantInput;

import java.time.LocalDateTime;

public record CreateDTO (String nomeRestaurante, String tipo, Address endereco, LocalDateTime abertura, LocalDateTime fechamento, String responsavel) {
    public static CreateRestaurantInput from(CreateDTO createDTO) {
        return new CreateRestaurantInput(
                createDTO.nomeRestaurante(),
                createDTO.tipo(),
                createDTO.endereco(),
                createDTO.abertura(),
                createDTO.fechamento(),
                createDTO.responsavel()
        );
    }
}
