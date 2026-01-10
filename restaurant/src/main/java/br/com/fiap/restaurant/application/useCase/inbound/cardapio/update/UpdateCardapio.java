package br.com.fiap.restaurant.application.useCase.inbound.cardapio.update;

import br.com.fiap.restaurant.application.useCase.inbound.cardapio.CardapioPorts;

public class UpdateCardapio {
    private final CardapioPorts cardapioPorts;

    public UpdateCardapio (CardapioPorts cardapioPorts) {
        this.cardapioPorts = cardapioPorts;
    }

    public UpdateCardapioOutput updateCardapio(UpdateCardapioInput input) {
        return cardapioPorts.updateCardapio(input);
    }
}
