package br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.AddressEntity;

import java.util.UUID;

public class AddressMapper implements IAddressMapper {

    public AddressMapper () {}
    @Override
    public AddressEntity toEntity(Address address) {
        return new AddressEntity(
                address.CEP(),
                address.logradouro(),
                address.bairro(),
                address.cidade()
        );
    }

    @Override
    public Address toDomain(AddressEntity entity) {
        return new Address(
                entity.getCEP(),
                entity.getLogradouro(),
                entity.getBairro(),
                entity.getCidade()
        );
    }
}
