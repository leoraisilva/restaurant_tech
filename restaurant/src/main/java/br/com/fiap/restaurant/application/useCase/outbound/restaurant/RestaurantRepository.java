package br.com.fiap.restaurant.application.useCase.outbound.restaurant;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;

public interface RestaurantRepository {
    Restaurant create(Restaurant restaurant);
    Restaurant update(Restaurant restaurant);
    Restaurant findByUsername(String Username);
    Pagination<Restaurant> findAll(Page page);
    Restaurant delete(Restaurant restaurant);
}
