package br.com.fiap.restaurant.application.domain.usuario;

public interface AddressFactory {
    Address newAddress(String CEP);
}
