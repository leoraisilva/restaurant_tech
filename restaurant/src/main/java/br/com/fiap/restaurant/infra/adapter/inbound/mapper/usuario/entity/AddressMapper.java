package br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.AddressEntity;

import java.util.UUID;

public class AddressMapper implements IAddressMapper {
    @Override
    public AddressEntity toEntity(Address address) {
        return new AddressEntity(
                UUID.randomUUID().toString(),
                address.cep(),
                address.logradouro(),
                address.bairro(),
                address.cidade(),
                address.numero()
        );
    }

    @Override
    public Address toDomain(AddressEntity entity) {
        return new Address(
                entity.getCep(),
                entity.getLogradouro(),
                entity.getBairro(),
                entity.getCidade(),
                entity.getNumero()
        );
    }
}
