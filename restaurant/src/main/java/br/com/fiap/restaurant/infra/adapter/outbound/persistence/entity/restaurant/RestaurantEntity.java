package br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.restaurant;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String restaurantId;
    @Column(name = "nome_restaurant", nullable = false, unique = true)
    private String nomeRestaurante;
    @Column(name = "tipo", nullable = false)
    private String tipo;
    @Column(name = "cep", nullable = false)
    private String CEP;
    @Column(name = "abertura", nullable = false)
    private LocalDateTime abertura;
    @Column(name = "fechamento", nullable = false)
    private LocalDateTime fechamento;
    @Column(name = "responsavel", nullable = false)
    private String responsavel;
    @Column(name = "actived")
    private boolean actived;
    @Column(name = "createAt")
    private LocalDateTime createAt;
    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    public RestaurantEntity(String restaurantId, String nomeRestaurante, String tipo, String CEP, LocalDateTime abertura, LocalDateTime fechamento, String responsavel, boolean actived, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.restaurantId = restaurantId;
        this.nomeRestaurante = nomeRestaurante;
        this.tipo = tipo;
        this.CEP = CEP;
        this.abertura = abertura;
        this.fechamento = fechamento;
        this.responsavel = responsavel;
        this.actived = actived;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public LocalDateTime getAbertura() {
        return abertura;
    }

    public void setAbertura(LocalDateTime abertura) {
        this.abertura = abertura;
    }

    public LocalDateTime getFechamento() {
        return fechamento;
    }

    public void setFechamento(LocalDateTime fechamento) {
        this.fechamento = fechamento;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
