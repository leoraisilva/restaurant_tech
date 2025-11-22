package br.com.fiap.restaurant.application.domain.cardapio;

import java.time.LocalDateTime;

public class DefaultCardapioFactory implements CardapioFactory{
    @Override
    public Cardapio newCardapio(String product, String descricao, double preco, String imagem, boolean entrega, String restaurant) {
        return new Cardapio.CardapioBuilder()
                .withNomeProduto(product)
                .withDescricao(descricao)
                .withPreco(preco)
                .withImagem(imagem)
                .withEntrega(entrega)
                .withRestaurant(restaurant)
                .withDisponivel(true)
                .withCreateAt(LocalDateTime.now())
                .withModifiedAt(LocalDateTime.now())
                .build();
    }
}
