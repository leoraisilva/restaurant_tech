package br.com.fiap.restaurant.application.useCase.inbound.restaurant.delete;

import br.com.fiap.restaurant.application.useCase.inbound.restaurant.RestaurantPorts;

public class DeleteRestaurant {
    private final RestaurantPorts restaurantPorts;

    public DeleteRestaurant(RestaurantPorts restaurantPorts) {
        this.restaurantPorts = restaurantPorts;
    }

    public DeleteRestaurantOutput deleteRestaurant(String nomeRestaurante) {
        return restaurantPorts.deleteRestaurant(nomeRestaurante);
    }
}
