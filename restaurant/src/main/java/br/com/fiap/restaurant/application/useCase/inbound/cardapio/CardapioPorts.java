package br.com.fiap.restaurant.application.useCase.inbound.cardapio;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapioInput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.delete.DeleteCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.get.GetCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.list.ListCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapioInput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapioOutput;

public interface CardapioPorts {
    CreateCardapioOutput createCardapio(CreateCardapioInput input);
    DeleteCardapioOutput deleteCardapio(String product);
    GetCardapioOutput getCardapio (String product);
    Pagination<ListCardapioOutput> listCardapio (Page page);
    UpdateCardapioOutput updateCardapio(UpdateCardapioInput input);
}
