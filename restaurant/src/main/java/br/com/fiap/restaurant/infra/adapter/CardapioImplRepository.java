package br.com.fiap.restaurant.infra.adapter;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.useCase.outbound.cardapio.CardapioRepository;

public class CardapioImplRepository implements CardapioRepository {
    @Override
    public Cardapio create(Cardapio cardapio) {
        return null;
    }

    @Override
    public Cardapio update(Cardapio cardapio) {
        return null;
    }

    @Override
    public Cardapio findByUsername(String Username) {
        return null;
    }

    @Override
    public Pagination<Cardapio> findAll(Page page) {
        return null;
    }

    @Override
    public Cardapio delete(Cardapio cardapio) {
        return null;
    }
}
