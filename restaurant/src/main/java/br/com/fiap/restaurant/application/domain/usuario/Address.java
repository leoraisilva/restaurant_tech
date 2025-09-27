package br.com.fiap.restaurant.application.domain.usuario;

import java.util.Objects;

public record Address (String CEP, String logradouro, String bairro, String cidade) {
    public Address {
        Objects.requireNonNull(CEP, "CEP Obrigatorio");
        if(CEP == null || !CEP.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP invalido");
        }
    }
}
