package br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.cardapio;

import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.cardapio.CardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardapioJPARespository extends JpaRepository<CardapioEntity, String> {
}
