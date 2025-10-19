package br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.AddressFactory;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.domain.usuario.UsuarioFactory;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IUsuarioMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.AddressEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.UsuarioEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class UsuarioMapper implements IUsuarioMapper {
    private final UsuarioFactory usuarioFactory;
    private final AddressFactory addressFactory;

    public UsuarioMapper(UsuarioFactory usuarioFactory, AddressFactory addressFactory) {
        this.usuarioFactory = usuarioFactory;
        this.addressFactory = addressFactory;
    }


    public Usuario toDomain(UsuarioEntity entity) {
        return usuarioFactory.newUsuario(
                entity.getNome(),
                entity.getUsername(),
                entity.getSenha(),
                entity.getEmail(),
                entity.getRegras(),
                addressFactory.newAddress(entity.getCEP()),
                entity.getNumero()
        );
    }

    public UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
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
