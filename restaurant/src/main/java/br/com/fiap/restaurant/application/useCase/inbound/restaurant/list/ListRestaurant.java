package br.com.fiap.restaurant.application.useCase.inbound.restaurant.list;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.list.ListCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.RestaurantPorts;

public class ListRestaurant {
    private final RestaurantPorts restaurantPorts;

    public ListRestaurant(RestaurantPorts restaurantPorts) {
        this.restaurantPorts = restaurantPorts;
    }

    public Pagination<ListRestaurantOutput> listRestaurant(Page page) {
        return this.restaurantPorts.listRestaurant(page);
    }
}
