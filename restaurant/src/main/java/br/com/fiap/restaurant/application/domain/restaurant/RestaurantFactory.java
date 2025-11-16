package br.com.fiap.restaurant.application.domain.restaurant;

import java.time.LocalDateTime;
import br.com.fiap.restaurant.application.domain.usuario.Address;

public interface RestaurantFactory {
    Restaurant newRestaurant(String nomeRestaurante, String tipo, Address endereco, LocalDateTime abertura, LocalDateTime fechamento, String responsavel);
}
