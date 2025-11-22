package br.com.fiap.restaurant.application.useCase.inbound.cardapio.get;

import br.com.fiap.restaurant.application.useCase.inbound.cardapio.CardapioPorts;

public class GetCardapio {
    private final CardapioPorts cardapioPorts;

    public GetCardapio (CardapioPorts cardapioPorts) {
        this.cardapioPorts = cardapioPorts;
    }

    public GetCardapioOutput getCardapio (String product) {
        return cardapioPorts.getCardapio(product);
    }
}
