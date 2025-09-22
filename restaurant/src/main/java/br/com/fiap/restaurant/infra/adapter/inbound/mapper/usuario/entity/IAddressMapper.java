package br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.AddressEntity;

public interface IAddressMapper {
    AddressEntity toEntity(Address address);
    Address toDomain(AddressEntity entity);
}
