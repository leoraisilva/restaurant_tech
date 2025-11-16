package br.com.fiap.restaurant.application.useCase.inbound.restaurant;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.delete.DeleteRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.get.GetRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.list.ListRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantOutput;

public interface RestaurantPorts {
    CreateRestaurantOutput createRestaurant(CreateRestaurantInput input);
    DeleteRestaurantOutput deleteRestaurant(String nomeRestaurante);
    GetRestaurantOutput getRestaurant(String nomeRestaurante);
    Pagination<ListRestaurantOutput> listRestaurant(Page page);
    UpdateRestaurantOutput updateRestaurant(UpdateRestaurantInput input);
}
