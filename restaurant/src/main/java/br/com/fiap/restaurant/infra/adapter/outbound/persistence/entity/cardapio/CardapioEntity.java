package br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.cardapio;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cardapio")
public class CardapioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cardapioId;
    @Column(name = "product", nullable = false, unique = true)
    private String product;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "preco", nullable = false)
    private double preco;
    @Column(name = "imagem")
    private String imagem;
    @Column(name = "entrega", nullable = false)
    private boolean entrega;
    @Column(name = "restaurant", nullable = false)
    private String restaurant;
    @Column(name = "disponivel")
    private boolean disponivel;
    @Column(name = "createAt")
    private LocalDateTime createAd;
    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    public CardapioEntity(String cardapioId, String product, String descricao, double preco, String imagem, boolean entrega, String restaurant, boolean disponivel, LocalDateTime createAd, LocalDateTime modifiedAt) {
        this.cardapioId = cardapioId;
        this.product = product;
        this.descricao = descricao;
        this.preco = preco;
        this.imagem = imagem;
        this.entrega = entrega;
        this.restaurant = restaurant;
        this.disponivel = disponivel;
        this.createAd = createAd;
        this.modifiedAt = modifiedAt;
    }

    public String getCardapioId() {
        return cardapioId;
    }

    public void setCardapioId(String cardapioId) {
        this.cardapioId = cardapioId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public LocalDateTime getCreateAd() {
        return createAd;
    }

    public void setCreateAd(LocalDateTime createAd) {
        this.createAd = createAd;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
