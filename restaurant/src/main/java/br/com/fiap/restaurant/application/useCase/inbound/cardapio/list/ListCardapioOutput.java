package br.com.fiap.restaurant.application.useCase.inbound.cardapio.list;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;

import java.time.LocalDateTime;

public record ListCardapioOutput (
        String product,
        String descricao,
        double preco,
        String imagem,
        boolean entrega,
        String restaurant,
        boolean disponivel,
        LocalDateTime createAd,
        LocalDateTime modifiedAt
) {

    public static ListCardapioOutput from(final Cardapio cardapio) {
        return new ListCardapioOutput(
                cardapio.getProduct(),
                cardapio.getDescricao(),
                cardapio.getPreco(),
                cardapio.getImagem(),
                cardapio.isEntrega(),
                cardapio.getRestaurant(),
                cardapio.isDisponivel(),
                cardapio.getCreateAd(),
                cardapio.getModifiedAt()
        );
    }

    public static Cardapio to(final ListCardapioOutput output){
        return new Cardapio.CardapioBuilder()
                .withNomeProduto(output.product())
                .withDescricao(output.descricao())
                .withPreco(output.preco())
                .withImagem(output.imagem())
                .withEntrega(output.entrega())
                .withRestaurant(output.restaurant())
                .withEntrega(output.entrega())
                .withDisponivel(output.disponivel())
                .withCreateAt(output.createAd())
                .withModifiedAt(output.modifiedAt())
                .build();
    }
}
