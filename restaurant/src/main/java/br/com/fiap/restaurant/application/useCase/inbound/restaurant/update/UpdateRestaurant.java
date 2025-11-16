package br.com.fiap.restaurant.application.useCase.inbound.restaurant.update;

import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapioInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.RestaurantPorts;

public class UpdateRestaurant {
    private final RestaurantPorts restaurantPorts;

    public UpdateRestaurant(RestaurantPorts restaurantPorts) {
        this.restaurantPorts = restaurantPorts;
    }

    public UpdateRestaurantOutput updateRestaurant(UpdateCardapioInput input) {
        return this.restaurantPorts.updateRestaurant(input);
    }
}
