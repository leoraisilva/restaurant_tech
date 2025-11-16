package br.com.fiap.restaurant.application.domain.cardapio;

import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;

import java.time.LocalDateTime;

public class Cardapio {
    private String nomeProduto;
    private String descricao;
    private double preco;
    private String imagem;
    private boolean entrega;
    private String restaurant;
    private boolean disponivel;
    private LocalDateTime createAd;
    private LocalDateTime modifiedAt;

    public Cardapio(CardapioBuilder builder){
        this.nomeProduto = builder.nomeProduto;
        this.descricao = builder.descricao;
        this.preco = builder.preco;
        this.imagem = builder.imagem;
        this.entrega = builder.entrega;
        this.restaurant = builder.restaurant;
        this.disponivel = builder.disponivel;
        this.createAd = builder.createAd;
        this.modifiedAt = builder.modifiedAt;
    }

    public String getNomeProduto() {
        return nomeProduto;
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

    public LocalDateTime getCreateAd() {
        return createAd;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public Cardapio update(String nomeProduto, String descricao, double preco, String imagem, boolean entrega){
        this.nomeProduto = nomeProduto;
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
        private String nomeProduto;
        private String descricao;
        private double preco;
        private String imagem;
        private boolean entrega;
        private String restaurant;
        private boolean disponivel;
        private LocalDateTime createAd;
        private LocalDateTime modifiedAt;

        public CardapioBuilder withNomeProduto(String nomeProduto) {
            this.nomeProduto = nomeProduto;
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

        public CardapioBuilder withCreateAt(LocalDateTime createAd) {
            this.createAd = createAd;
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
