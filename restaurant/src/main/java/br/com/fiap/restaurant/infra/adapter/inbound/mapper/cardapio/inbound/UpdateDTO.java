package br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.inbound;

import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapioInput;

public record UpdateDTO (String product, String descricao, double preco, String imagem, boolean entrega) {
    public static UpdateCardapioInput from(UpdateDTO updateDTO) {
        return new UpdateCardapioInput(
                updateDTO.product(),
                updateDTO.descricao(),
                updateDTO.preco(),
                updateDTO.imagem(),
                updateDTO.entrega()
        );
    }
}
