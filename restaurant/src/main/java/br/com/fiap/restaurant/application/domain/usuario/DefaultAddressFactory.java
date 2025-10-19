package br.com.fiap.restaurant.application.domain.usuario;

public class DefaultAddressFactory implements AddressFactory {

    public DefaultAddressFactory() {}
    @Override
    public Address newAddress(String CEP) {
        return new Address(CEP, "", "", "");
    }
}
