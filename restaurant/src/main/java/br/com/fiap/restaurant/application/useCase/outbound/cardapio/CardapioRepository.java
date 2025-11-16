package br.com.fiap.restaurant.application.useCase.outbound.cardapio;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;


public interface CardapioRepository {
    Cardapio create(Cardapio cardapio);
    Cardapio update(Cardapio cardapio);
    Cardapio findByUsername(String Username);
    Pagination<Cardapio> findAll();
    Cardapio delete(Cardapio cardapio);
}
