package br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.inbound;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.create.CreateUsuarioInput;

import java.time.LocalDateTime;
import java.util.Objects;

public record CreateDTO(String nome, String usuario, String senha, String email, Role regras, Address endereco) {
    public CreateDTO {
        Objects.requireNonNull(nome);
        Objects.requireNonNull(usuario);
        Objects.requireNonNull(email);
        Objects.requireNonNull(endereco.cep());
        if (email.matches("^^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")){
            throw new IllegalArgumentException("E-mail Invalido");
        }
    }

    public CreateUsuarioInput to(CreateDTO createDTO){
        return new CreateUsuarioInput(
                createDTO.nome(),
                createDTO.usuario(),
                createDTO.senha(),
                createDTO.email(),
                createDTO.regras(),
                createDTO.endereco()
        );
    }
}
