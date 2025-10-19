package br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity;

import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.UsuarioEntity;

public interface IUsuarioMapper {
    Usuario toDomain(UsuarioEntity entity);
    UsuarioEntity toEntity(Usuario usuario);
}
