package br.com.fiap.restaurant.application.services;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.restaurant.RestaurantFactory;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.RestaurantPorts;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.delete.DeleteRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.get.GetRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.list.ListRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.outbound.restaurant.RestaurantRepository;

public class RestaurantService implements RestaurantPorts {
    private final RestaurantRepository repository;
    private final RestaurantFactory factory;

    public RestaurantService(RestaurantRepository repository, RestaurantFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public CreateRestaurantOutput createRestaurant(CreateRestaurantInput input) {
        var restaurant = factory.newRestaurant(input.nomeRestaurante(), input.tipo(), input.endereco(), input.abertura(), input.fechamento(), input.responsavel());
        return CreateRestaurantOutput.from(restaurant);
    }

    @Override
    public DeleteRestaurantOutput deleteRestaurant(String nomeRestaurante) {
        var restaurant = repository.findByNomeRestaurante(nomeRestaurante);
        return DeleteRestaurantOutput.from(repository.delete(restaurant));
    }

    @Override
    public GetRestaurantOutput getRestaurant(String nomeRestaurante) {
        var restaurant = repository.findByNomeRestaurante(nomeRestaurante);
        return GetRestaurantOutput.from(restaurant);
    }

    @Override
    public Pagination<ListRestaurantOutput> listRestaurant(Page page) {
        return repository.findAll(page).mapItems(ListRestaurantOutput::from);
    }

    @Override
    public UpdateRestaurantOutput updateRestaurant(UpdateRestaurantInput input) {
        var restaurant = repository.findByNomeRestaurante(input.nomeRestaurante());
        restaurant.update(input.nomeRestaurante(), input.tipo(), input.endereco(), input.abertura(), input.fechamento());
        return UpdateRestaurantOutput.from(repository.update(restaurant));
    }
}
