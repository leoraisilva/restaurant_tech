package br.com.fiap.restaurant.application.useCase.inbound.restaurant.create;

import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.domain.usuario.Address;

import java.time.LocalDateTime;

public record CreateRestaurantOutput (
        String nomeRestaurante,
        String tipo,
        Address endereco,
        LocalDateTime abertura,
        LocalDateTime fechamento,
        String responsavel,
        boolean actived,
        LocalDateTime createAt,
        LocalDateTime modifiedAt
) {
    public CreateRestaurantOutput from(final Restaurant restaurant){
        return new CreateRestaurantOutput(
                restaurant.getNomeRestaurante(),
                restaurant.getTipo(),
                restaurant.getEndereco(),
                restaurant.getAbertura(),
                restaurant.getFechamento(),
                restaurant.getResponsavel(),
                restaurant.isActived(),
                restaurant.getCreateAt(),
                restaurant.getModifiedAt()
        );
    }

    public Restaurant to(final CreateRestaurantOutput output){
        return new Restaurant.RestaurantBuilder()
                .withNomeRestaurant(output.nomeRestaurante())
                .withTipo(output.tipo())
                .withEndereco(output.endereco())
                .withAbertura(output.abertura())
                .withFechamento(output.fechamento())
                .withResponsavel(output.responsavel())
                .withActived(output.actived())
                .withCreatedAt(output.createAt())
                .withModifiedAt(output.modifiedAt())
                .build();
    }
}
