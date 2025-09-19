package br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario;

import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJPARepository extends JpaRepository<UsuarioEntity, String> {
}
