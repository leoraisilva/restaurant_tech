package br.com.fiap.restaurant.application.domain.cardapio;

import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;

import java.time.LocalDateTime;

public class Cardapio {
    private String product;
    private String descricao;
    private double preco;
    private String imagem;
    private boolean entrega;
    private String restaurant;
    private boolean disponivel;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Cardapio(CardapioBuilder builder){
        this.product = builder.product;
        this.descricao = builder.descricao;
        this.preco = builder.preco;
        this.imagem = builder.imagem;
        this.entrega = builder.entrega;
        this.restaurant = builder.restaurant;
        this.disponivel = builder.disponivel;
        this.createdAt = builder.createdAt;
        this.modifiedAt = builder.modifiedAt;
    }

    public String getProduct() {
        return product;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public String getImagem() {
        return imagem;
    }

    public boolean isEntrega() {
        return entrega;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public Cardapio update(String product, String descricao, double preco, String imagem, boolean entrega){
        this.product = product;
        this.descricao = descricao;
        this.preco = preco;
        this.imagem = imagem;
        this.entrega = entrega;
        return this;
    }

    public Cardapio delete() {
        if(!isDisponivel())
             return this;
        this.disponivel = false;
        return this;
    }

    public static class CardapioBuilder {
        private String product;
        private String descricao;
        private double preco;
        private String imagem;
        private boolean entrega;
        private String restaurant;
        private boolean disponivel;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public CardapioBuilder withNomeProduto(String product) {
            this.product = product;
            return this;
        }

        public CardapioBuilder withDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public CardapioBuilder withPreco(double preco) {
            this.preco = preco;
            return this;
        }

        public CardapioBuilder withImagem(String imagem) {
            this.imagem = imagem;
            return this;
        }

        public CardapioBuilder withEntrega(boolean entrega) {
            this.entrega = entrega;
            return this;
        }

        public CardapioBuilder withDisponivel(boolean disponivel) {
            this.disponivel = disponivel;
            return this;
        }

        public CardapioBuilder withRestaurant(String restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public CardapioBuilder withCreateAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CardapioBuilder withModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public Cardapio build() {
            return new Cardapio(this);
        }

    }
}
