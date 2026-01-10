package br.com.fiap.restaurant.application.domain.restaurant;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;

import java.time.LocalDateTime;

public class Restaurant {
    private String nomeRestaurante;
    private String tipo;
    private Address endereco;
    private LocalDateTime abertura;
    private LocalDateTime fechamento;
    private String responsavel;
    private boolean actived;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public Restaurant(RestaurantBuilder builder){
        this.nomeRestaurante = builder.nomeRestaurante;
        this.tipo = builder.tipo;
        this.endereco = builder.endereco;
        this.abertura = builder.abertura;
        this.fechamento = builder.fechamento;
        this.responsavel = builder.responsavel;
        this.actived = builder.actived;
        this.createAt = builder.createAt;
        this.modifiedAt = builder.modifiedAt;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public String getTipo() {
        return tipo;
    }

    public Address getEndereco() {
        return endereco;
    }

    public LocalDateTime getAbertura() {
        return abertura;
    }

    public LocalDateTime getFechamento() {
        return fechamento;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public boolean isActived() {
        return actived;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public Restaurant update(String nomeRestaurante, String tipo, Address endereco, LocalDateTime abertura, LocalDateTime fechamento) {
        this.nomeRestaurante = nomeRestaurante;
        this.tipo = tipo;
        this.endereco = endereco;
        this.abertura = abertura;
        this.fechamento = fechamento;
        return this;
    }

    public Restaurant delete() {
        if(!isActived())
            return this;
        this.actived = false;
        return this;
    }

    public Restaurant updateOwner (String responsavel) {
        if(!isActived())
            return this;
        this.responsavel = responsavel;
        return this;
    }

    public static class RestaurantBuilder {
        private String nomeRestaurante;
        private String tipo;
        private Address endereco;
        private LocalDateTime abertura;
        private LocalDateTime fechamento;
        private String responsavel;
        private boolean actived;
        private LocalDateTime createAt;
        private LocalDateTime modifiedAt;

        public RestaurantBuilder withNomeRestaurant(String nomeRestaurant){
            this.nomeRestaurante = nomeRestaurant;
            return this;
        }

        public RestaurantBuilder withTipo(String tipo){
            this.tipo = tipo;
            return this;
        }

        public RestaurantBuilder withEndereco(Address endereco){
            this.endereco = endereco;
            return this;
        }

        public RestaurantBuilder withAbertura(LocalDateTime abertura){
            this.abertura = abertura;
            return this;
        }

        public RestaurantBuilder withFechamento(LocalDateTime fechamento){
            this.fechamento = fechamento;
            return this;
        }

        public RestaurantBuilder withResponsavel(String responsavel){
            this.responsavel = responsavel;
            return this;
        }

        public RestaurantBuilder withActived(boolean actived){
            this.actived = actived;
            return this;
        }

        public RestaurantBuilder withCreatedAt(LocalDateTime createdAt){
            this.createAt = createdAt;
            return this;
        }

        public RestaurantBuilder withModifiedAt(LocalDateTime modifiedAt){
            this.modifiedAt = modifiedAt;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }


}
