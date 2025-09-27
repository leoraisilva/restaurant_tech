package br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.inbound;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.create.CreateUsuarioInput;

import java.time.LocalDateTime;
import java.util.Objects;

public record CreateDTO(String nome, String username, String senha, String email, Role regras, Address endereco, Integer numero) {
    public CreateDTO {
        Objects.requireNonNull(nome);
        Objects.requireNonNull(username);
        Objects.requireNonNull(email);
        Objects.requireNonNull(endereco.CEP());
        if (email.matches("^^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")){
            throw new IllegalArgumentException("E-mail Invalido");
        }
    }

    public CreateUsuarioInput to(CreateDTO createDTO){
        return new CreateUsuarioInput(
                createDTO.nome(),
                createDTO.username(),
                createDTO.senha(),
                createDTO.email(),
                createDTO.regras(),
                createDTO.endereco(),
                createDTO.numero()
        );
    }
}
