package br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.cardapio;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.cardapio.CardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardapioJPARespository extends JpaRepository<CardapioEntity, String> {
    CardapioEntity findByProduct(String product);
}
