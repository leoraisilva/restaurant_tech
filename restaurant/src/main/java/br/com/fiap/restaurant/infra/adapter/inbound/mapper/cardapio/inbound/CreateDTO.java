package br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.inbound;

import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapioInput;

public record CreateDTO (String product, String descricao, double preco, String imagem, boolean entrega, String restaurant) {
    public static CreateCardapioInput from(CreateDTO createDTO) {
        return new CreateCardapioInput(
                createDTO.product(),
                createDTO.descricao(),
                createDTO.preco(),
                createDTO.imagem(),
                createDTO.entrega(),
                createDTO.restaurant()
        );
    }
}
