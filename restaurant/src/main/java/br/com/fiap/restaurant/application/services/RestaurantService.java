package br.com.fiap.restaurant.application.services;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.domain.restaurant.RestaurantFactory;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.RestaurantPorts;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.delete.DeleteRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.get.GetRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.list.ListRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.outbound.restaurant.RestaurantRepository;
import br.com.fiap.restaurant.application.useCase.outbound.usuario.UsuarioRepository;

public class RestaurantService implements RestaurantPorts {
    private final RestaurantRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final RestaurantFactory factory;

    public RestaurantService(RestaurantRepository repository, RestaurantFactory factory, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.factory = factory;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public CreateRestaurantOutput createRestaurant(CreateRestaurantInput input) {
        var responsability = usuarioRepository.findByUsername(input.getResponsavel());
        if(responsability == null || responsability.getRegras().equals(Role.CLIENT)) input.setResponsavel(null);
        var nomeRestaurant = repository.findByNomeRestaurante(input.getNomeRestaurante());
        if(nomeRestaurant != null) throw new IllegalArgumentException("Ja existe Restaurant com esse nome");
        var restaurant = factory.newRestaurant(input.getNomeRestaurante(), input.getTipo(), input.getEndereco(), input.getAbertura(), input.getFechamento(), input.getResponsavel());
        return CreateRestaurantOutput.from(repository.create(restaurant));
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
