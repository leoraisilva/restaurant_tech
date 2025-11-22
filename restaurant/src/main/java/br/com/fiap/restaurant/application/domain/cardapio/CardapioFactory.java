package br.com.fiap.restaurant.application.domain.cardapio;

public interface CardapioFactory {
    Cardapio newCardapio(String product, String descricao, double preco, String imagem, boolean entrega, String restaurant);
}
