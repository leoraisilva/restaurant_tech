package br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.domain.usuario.UsuarioFactory;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IUsuarioMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.AddressEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.UsuarioEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class UsuarioMapper implements IUsuarioMapper {
    private final UsuarioFactory factory;

    public UsuarioMapper(UsuarioFactory factory, Address address, AddressEntity addressEntity) {
        this.factory = factory;
    }

    public UsuarioMapper(UsuarioFactory factory) {
        this.factory = factory;
    }


    public Usuario toDomain(UsuarioEntity entity) {
        return factory.newUsuario(
                entity.getNome(),
                entity.getUsername(),
                entity.getSenha(),
                entity.getEmail(),
                entity.getRegras(),
                null,
                entity.getNumero()
        );
    }

    public UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
                UUID.randomUUID().toString(),
                usuario.getNome(),
                usuario.getUsername(),
                usuario.getSenha(),
                usuario.getEmail(),
                usuario.getRegras(),
                usuario.getEndereco().CEP(),
                usuario.getNumero(),
                usuario.isActived(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
