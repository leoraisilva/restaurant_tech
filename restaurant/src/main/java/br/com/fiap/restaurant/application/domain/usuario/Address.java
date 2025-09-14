package br.com.fiap.restaurant.application.domain.usuario;

import java.util.Objects;

public record Address (String cep, String logradouro, String bairro, String cidade, int numero) {
    public Address {
        Objects.requireNonNull(cep, "CEP Obrigatorio");
        if(cep == null || !cep.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP invalido");
        }
    }
}
