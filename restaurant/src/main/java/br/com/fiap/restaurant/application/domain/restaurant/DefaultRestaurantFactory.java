package br.com.fiap.restaurant.application.domain.restaurant;

import br.com.fiap.restaurant.application.domain.usuario.Address;

import java.time.LocalDateTime;
import java.util.Objects;

public class DefaultRestaurantFactory implements RestaurantFactory{

    @Override
    public Restaurant newRestaurant(String nomeRestaurante, String tipo, Address endereco, LocalDateTime abertura, LocalDateTime fechamento, String responsavel) {
        Objects.requireNonNull(nomeRestaurante, "Nome do Restaurante Obrigatório");

        if(verifyResponsibility(responsavel)) {
            return new Restaurant.RestaurantBuilder()
                    .withNomeRestaurant(nomeRestaurante)
                    .withTipo(tipo)
                    .withEndereco(endereco)
                    .withAbertura(abertura)
                    .withFechamento(fechamento)
                    .withResponsavel(responsavel)
                    .withActived(true)
                    .withCreatedAt(LocalDateTime.now())
                    .withModifiedAt(LocalDateTime.now())
                    .build();
        }
        throw new IllegalArgumentException("Usuario não Existe!!");
    }

    private boolean verifyResponsibility(String responsavel) {
        if (responsavel != null)
            return true;
        return false;
    }

}
