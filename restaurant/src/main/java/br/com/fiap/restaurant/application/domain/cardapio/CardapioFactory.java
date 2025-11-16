package br.com.fiap.restaurant.application.domain.cardapio;

public interface CardapioFactory {
    Cardapio newCardapio(String nomeProduto, String descricao, double preco, String imagem, boolean entrega, String restaurant);
}
