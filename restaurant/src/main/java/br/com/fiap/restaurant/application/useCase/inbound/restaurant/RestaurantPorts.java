package br.com.fiap.restaurant.application.useCase.inbound.restaurant;

import br.com.fiap.restaurant.application.useCase.inbound.cardapio.get.GetCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.list.ListCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapioInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.delete.DeleteRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantOutput;

public interface RestaurantPorts {
    CreateRestaurantOutput createRestaurant(CreateRestaurantInput input);
    DeleteRestaurantOutput deleteRestaurant(String nomeRestaurante);
    GetCardapioOutput getRestaurant(String nomeRestaurante);
    ListCardapioOutput listRestaurant();
    UpdateRestaurantOutput updateRestaurant(UpdateCardapioInput input);
}
