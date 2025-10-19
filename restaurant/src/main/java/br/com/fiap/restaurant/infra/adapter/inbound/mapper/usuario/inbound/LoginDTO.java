package br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.inbound;

import br.com.fiap.restaurant.application.useCase.inbound.usuario.change.ChangeUsuarioInput;

public record LoginDTO(String username, String senha) {
    public ChangeUsuarioInput to(LoginDTO loginDTO){
        return new ChangeUsuarioInput(
                loginDTO.username(),
                loginDTO.senha()
        );
    }
}
