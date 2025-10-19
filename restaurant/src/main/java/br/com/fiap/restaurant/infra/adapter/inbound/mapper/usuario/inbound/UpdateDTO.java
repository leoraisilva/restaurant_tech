package br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.inbound;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.update.UpdateUsuarioInput;

import java.util.Objects;

public record UpdateDTO(String nome, String username, String email, Address endereco, Integer numero) {
    public UpdateDTO {
        Objects.requireNonNull(nome, "Nome Obrigatorio");
        if(nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome Obrigatorio");
        Objects.requireNonNull(email, "E-mail Obrigatorio");
        if(email.trim().isEmpty())
            throw new IllegalArgumentException("E-mail Obrigatorio");
        Objects.requireNonNull(endereco.CEP(), "CEP Obrigatorio");
    }

    public UpdateUsuarioInput to(UpdateDTO updateDTO){
        return new UpdateUsuarioInput(
                updateDTO.nome(),
                updateDTO.username(),
                updateDTO.email(),
                updateDTO.endereco(),
                updateDTO.numero()
        );
    }
}
