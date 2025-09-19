package br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.domain.usuario.UsuarioFactory;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.AddressEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.UsuarioEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class UsuarioMapper {
    private final UsuarioFactory factory;
    private final AddressEntity addressEntity;
    private final Address address;

    public UsuarioMapper(UsuarioFactory factory, Address address, AddressEntity addressEntity) {
        this.factory = factory;
        this.address = address;
        this.addressEntity = addressEntity;
    }

    public Usuario toDomain(UsuarioEntity entity) {
        return factory.newUsuario(
                entity.getNome(),
                entity.getUsuario(),
                entity.getSenha(),
                entity.getEmail(),
                entity.getRegras(),
                entity.getIdEndereco().equals(addressEntity.getIdEndereco()) ? new Address(
                        addressEntity.getCep(),
                        addressEntity.getLogradouro(),
                        addressEntity.getBairro(),
                        addressEntity.getCidade(),
                        addressEntity.getNumero()) : null
        );
    }

    public UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
                UUID.randomUUID().toString(),
                usuario.getNome(),
                usuario.getUsuario(),
                usuario.getSenha(),
                usuario.getEmail(),
                usuario.getRegras(),
                usuario.getEndereco().cep().equals(addressEntity.getCep()) ? addressEntity.getIdEndereco() : null,
                usuario.isActived(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
