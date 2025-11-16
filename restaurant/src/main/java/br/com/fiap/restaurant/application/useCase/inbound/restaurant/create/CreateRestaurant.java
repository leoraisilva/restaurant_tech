package br.com.fiap.restaurant.application.useCase.inbound.restaurant.create;

import br.com.fiap.restaurant.application.useCase.inbound.restaurant.RestaurantPorts;

public class CreateRestaurant {
    private final RestaurantPorts restaurantPorts;

    public CreateRestaurant(RestaurantPorts restaurantPorts) {
        this.restaurantPorts = restaurantPorts;
    }

    public CreateRestaurantOutput createRestaurant (CreateRestaurantInput input) {
        return restaurantPorts.createRestaurant(input);
    }
}
