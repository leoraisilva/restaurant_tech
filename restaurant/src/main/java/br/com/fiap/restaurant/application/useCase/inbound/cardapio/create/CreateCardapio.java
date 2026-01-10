package br.com.fiap.restaurant.application.useCase.inbound.cardapio.create;

import br.com.fiap.restaurant.application.useCase.inbound.cardapio.CardapioPorts;

public class CreateCardapio {
    private final CardapioPorts cardapioPorts;

    public CreateCardapio (CardapioPorts cardapioPorts) {
        this.cardapioPorts = cardapioPorts;
    }

    public CreateCardapioOutput createCardapio(CreateCardapioInput input) {
        return cardapioPorts.createCardapio(input);
    }
}
