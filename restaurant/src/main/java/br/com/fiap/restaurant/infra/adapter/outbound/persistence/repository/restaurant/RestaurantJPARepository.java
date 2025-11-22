package br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.restaurant;

import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.restaurant.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantJPARepository extends JpaRepository<RestaurantEntity, String> {
    RestaurantEntity findByNomeRestaurante(String nomeRestaurante);
}
