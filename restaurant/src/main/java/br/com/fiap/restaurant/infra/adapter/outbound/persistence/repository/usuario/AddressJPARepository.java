package br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario;

import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressJPARepository extends JpaRepository<AddressEntity, String>{
    AddressEntity findByCEP(String cep);
}
