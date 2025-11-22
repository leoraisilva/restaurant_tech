package br.com.fiap.restaurant.application.useCase.inbound.cardapio.update;

public record UpdateCardapioInput (String product, String descricao, double preco, String imagem, boolean entrega) {
}
