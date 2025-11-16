package br.com.fiap.restaurant.application.useCase.inbound.cardapio.delete;

import br.com.fiap.restaurant.application.useCase.inbound.cardapio.CardapioPorts;

public class DeleteCardapio {
    private final CardapioPorts cardapioPorts;

    public DeleteCardapio (CardapioPorts cardapioPorts) {
        this.cardapioPorts = cardapioPorts;
    }

    public DeleteCardapioOutput deleteCardapio (String nomeProduto) {
        return cardapioPorts.deleteCardapio(nomeProduto);
    }
}
