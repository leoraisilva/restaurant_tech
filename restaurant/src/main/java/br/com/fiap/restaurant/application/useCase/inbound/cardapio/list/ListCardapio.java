package br.com.fiap.restaurant.application.useCase.inbound.cardapio.list;

import br.com.fiap.restaurant.application.useCase.inbound.cardapio.CardapioPorts;

public class ListCardapio {
    private final CardapioPorts cardapioPorts;

    public ListCardapio (CardapioPorts cardapioPorts) {
        this.cardapioPorts = cardapioPorts;
    }

    public ListCardapioOutput listCardapio () {
        return cardapioPorts.listCardapio();
    }
}
