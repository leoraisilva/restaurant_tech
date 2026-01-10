package br.com.fiap.restaurant.application.useCase.inbound.cardapio.get;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;

import java.time.LocalDateTime;

public record GetCardapioOutput (
        String product,
        String descricao,
        double preco,
        String imagem,
        boolean entrega,
        String restaurant,
        boolean disponivel,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static GetCardapioOutput from(final Cardapio cardapio) {
        return new GetCardapioOutput(
                cardapio.getProduct(),
                cardapio.getDescricao(),
                cardapio.getPreco(),
                cardapio.getImagem(),
                cardapio.isEntrega(),
                cardapio.getRestaurant(),
                cardapio.isDisponivel(),
                cardapio.getCreatedAt(),
                cardapio.getModifiedAt()
        );
    }

    public static Cardapio to(final GetCardapioOutput output){
        return new Cardapio.CardapioBuilder()
                .withNomeProduto(output.product())
                .withDescricao(output.descricao())
                .withPreco(output.preco())
                .withImagem(output.imagem())
                .withEntrega(output.entrega())
                .withRestaurant(output.restaurant())
                .withEntrega(output.entrega())
                .withDisponivel(output.disponivel())
                .withCreateAt(output.createdAt())
                .withModifiedAt(output.modifiedAt())
                .build();
    }
}
