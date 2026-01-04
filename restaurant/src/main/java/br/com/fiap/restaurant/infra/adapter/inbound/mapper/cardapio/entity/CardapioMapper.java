package br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.entity;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.domain.cardapio.CardapioFactory;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.cardapio.CardapioEntity;

import java.util.UUID;

public class CardapioMapper implements ICardapioMapper{
    private final CardapioFactory factory;

    public CardapioMapper(CardapioFactory factory) {
        this.factory = factory;
    }
    @Override
    public Cardapio toDomain(CardapioEntity entity) {
        return factory.newCardapio(
                entity.getProduct(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getImagem(),
                entity.isEntrega(),
                entity.getRestaurant()
        );
    }

    @Override
    public CardapioEntity toEntity(Cardapio cardapio) {
        return new CardapioEntity(
                cardapio.getProduct(),
                cardapio.getDescricao(),
                cardapio.getPreco(),
                cardapio.getImagem(),
                cardapio.isEntrega(),
                cardapio.getRestaurant(),
                cardapio.isDisponivel(),
                cardapio.getCreatedAt(),
                cardapio.getModifiedAt()
        );
    }
}
