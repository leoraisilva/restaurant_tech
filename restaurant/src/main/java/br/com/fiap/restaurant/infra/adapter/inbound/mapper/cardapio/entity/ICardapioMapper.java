package br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.entity;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.cardapio.CardapioEntity;

public interface ICardapioMapper {
    Cardapio toDomain(CardapioEntity entity);
    CardapioEntity toEntity(Cardapio cardapio);
}
