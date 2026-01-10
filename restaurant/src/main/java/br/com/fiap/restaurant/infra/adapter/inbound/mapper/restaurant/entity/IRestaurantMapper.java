package br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.entity;

import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.restaurant.RestaurantEntity;

public interface IRestaurantMapper {
    Restaurant toDomain(RestaurantEntity entity);
    RestaurantEntity toEntity(Restaurant restaurant);
}
