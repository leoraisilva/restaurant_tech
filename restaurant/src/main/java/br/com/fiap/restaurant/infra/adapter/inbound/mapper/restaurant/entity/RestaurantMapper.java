package br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.entity;

import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.domain.restaurant.RestaurantFactory;
import br.com.fiap.restaurant.application.domain.usuario.AddressFactory;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.restaurant.RestaurantEntity;

import java.util.UUID;

public class RestaurantMapper implements IRestaurantMapper{
    private final RestaurantFactory restaurantFactory;
    private final AddressFactory addressFactory;

    public RestaurantMapper(RestaurantFactory restaurantFactory, AddressFactory addressFactory) {
        this.restaurantFactory = restaurantFactory;
        this.addressFactory = addressFactory;
    }

    @Override
    public Restaurant toDomain(RestaurantEntity entity) {
        return restaurantFactory.newRestaurant(
                entity.getNomeRestaurante(),
                entity.getTipo(),
                addressFactory.newAddress(entity.getCEP()),
                entity.getAbertura(),
                entity.getFechamento(),
                entity.getResponsavel()
        );
    }

    @Override
    public RestaurantEntity toEntity(Restaurant restaurant) {
        return new RestaurantEntity(
                restaurant.getNomeRestaurante(),
                restaurant.getTipo(),
                restaurant.getEndereco().CEP(),
                restaurant.getAbertura(),
                restaurant.getFechamento(),
                restaurant.getResponsavel(),
                restaurant.isActived(),
                restaurant.getCreateAt(),
                restaurant.getModifiedAt()
        );
    }
}
