package br.com.fiap.restaurant.application.services;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.domain.cardapio.CardapioFactory;
import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.CardapioPorts;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapioInput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.delete.DeleteCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.get.GetCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.list.ListCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapioInput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapioOutput;
import br.com.fiap.restaurant.application.useCase.outbound.cardapio.CardapioRepository;
import br.com.fiap.restaurant.application.useCase.outbound.restaurant.RestaurantRepository;

import java.util.Objects;

public class CardapioService implements CardapioPorts {
    private final CardapioFactory factory;
    private final RestaurantRepository restaurantRepository;
    private final CardapioRepository repository;

    public CardapioService(CardapioFactory factory, RestaurantRepository restaurantRepository, CardapioRepository repository) {
        Objects.requireNonNull(repository);
        this.factory = factory;
        this.restaurantRepository = restaurantRepository;
        this.repository = repository;
    }

    @Override
    public CreateCardapioOutput createCardapio(CreateCardapioInput input) {
        var restaurant = restaurantRepository.findByNomeRestaurante(input.restaurant());
        Cardapio cardapio;
        if(restaurant == null){
            cardapio = factory.newCardapio(input.product(),input.descricao(), input.preco(), input.imagem(), input.entrega(),null);
            return CreateCardapioOutput.from(repository.create(cardapio));
        }
        cardapio = factory.newCardapio(input.product(),input.descricao(), input.preco(), input.imagem(), input.entrega(),input.restaurant());
        return CreateCardapioOutput.from(repository.create(cardapio));
    }

    @Override
    public DeleteCardapioOutput deleteCardapio(String product) {
        var cardapio = repository.findByProduct(product);
        return DeleteCardapioOutput.from(repository.delete(cardapio));
    }

    @Override
    public GetCardapioOutput getCardapio(String product) {
        var cardapio = repository.findByProduct(product);
        return GetCardapioOutput.from(cardapio);
    }

    @Override
    public Pagination<ListCardapioOutput> listCardapio(Page page) {
        return repository.findAll(page).mapItems(ListCardapioOutput::from);
    }

    @Override
    public UpdateCardapioOutput updateCardapio(UpdateCardapioInput input) {
        var cardapio = repository.findByProduct(input.product());
        cardapio.update(input.product(), input.descricao(), input.preco(), input.imagem(), input.entrega());
        return UpdateCardapioOutput.from(repository.update(cardapio));
    }
}
