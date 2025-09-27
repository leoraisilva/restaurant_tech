package br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.inbound;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.update.UpdateUsuarioInput;

import java.util.Objects;

public record UpdateDTO(String nome, String username, String senha, String email, Address endereco, Integer numero, boolean actived) {
    public UpdateDTO {
        Objects.requireNonNull(nome);
        Objects.requireNonNull(username);
        Objects.requireNonNull(email);
        Objects.requireNonNull(endereco.CEP());
        if (email.matches("^^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")){
            throw new IllegalArgumentException("E-mail Invalido");
        }
    }

    public UpdateUsuarioInput to(UpdateDTO updateDTO){
        return new UpdateUsuarioInput(
                updateDTO.nome(),
                updateDTO.username(),
                updateDTO.senha(),
                updateDTO.email(),
                updateDTO.endereco(),
                updateDTO.numero(),
                updateDTO.actived()
        );
    }
}
