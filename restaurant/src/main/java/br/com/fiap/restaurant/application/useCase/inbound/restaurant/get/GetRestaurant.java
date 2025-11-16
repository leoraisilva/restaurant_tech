package br.com.fiap.restaurant.application.useCase.inbound.restaurant.get;

import br.com.fiap.restaurant.application.useCase.inbound.cardapio.get.GetCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.RestaurantPorts;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.delete.DeleteRestaurantOutput;

public class GetRestaurant {
    private final RestaurantPorts restaurantPorts;

    public GetRestaurant(RestaurantPorts restaurantPorts) {
        this.restaurantPorts = restaurantPorts;
    }

    public GetCardapioOutput getRestaurant(String nomeRestaurante) {
        return restaurantPorts.getRestaurant(nomeRestaurante);
    }
}
