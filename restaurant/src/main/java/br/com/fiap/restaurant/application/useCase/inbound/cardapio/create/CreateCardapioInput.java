package br.com.fiap.restaurant.application.useCase.inbound.cardapio.create;

public record CreateCardapioInput(String product, String descricao, double preco, String imagem, boolean entrega, String restaurant) {
}
