package br.com.fiap.restaurant.infra.adapter;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.useCase.outbound.restaurant.RestaurantRepository;

public class RestaurantImplRepository implements RestaurantRepository {
    @Override
    public Restaurant create(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant findByUsername(String Username) {
        return null;
    }

    @Override
    public Pagination<Restaurant> findAll(Page page) {
        return null;
    }

    @Override
    public Restaurant delete(Restaurant restaurant) {
        return null;
    }
}
