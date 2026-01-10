package br.com.fiap.restaurant.application.useCase.inbound.restaurant.create;

import br.com.fiap.restaurant.application.domain.usuario.Address;

import java.time.LocalDateTime;

public class CreateRestaurantInput{
    private String nomeRestaurante;
    private String tipo;
    private Address endereco;
    private LocalDateTime abertura;
    private LocalDateTime fechamento;
    private String responsavel;

    public CreateRestaurantInput(String nomeRestaurante, String tipo, Address endereco, LocalDateTime abertura, LocalDateTime fechamento, String responsavel) {
        this.nomeRestaurante = nomeRestaurante;
        this.tipo = tipo;
        this.endereco = endereco;
        this.abertura = abertura;
        this.fechamento = fechamento;
        this.responsavel = responsavel;
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

    public Address getEndereco() {
        return endereco;
    }

    public void setEndereco(Address endereco) {
        this.endereco = endereco;
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
}
