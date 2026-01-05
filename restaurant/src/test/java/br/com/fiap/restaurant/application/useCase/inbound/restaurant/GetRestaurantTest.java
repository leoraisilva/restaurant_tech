package br.com.fiap.restaurant.application.useCase.inbound.restaurant;

import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.get.GetRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.get.GetRestaurantOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetRestaurantTest {
    @Mock
    private RestaurantPorts restaurantPorts;

    @InjectMocks
    private GetRestaurant getRestaurant;

    @Test
    void deveCriarGetRestaurantOutputAPartirDeRestaurant() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        LocalDateTime abertura = LocalDateTime.of(2024, 2, 1, 9, 0);
        LocalDateTime fechamento = LocalDateTime.of(2024, 2, 1, 23, 0);
        var now = LocalDateTime.now();

        Restaurant restaurant = new Restaurant.RestaurantBuilder()
                .withNomeRestaurant("Restaurant X")
                .withTipo("Italiano")
                .withEndereco(endereco)
                .withAbertura(abertura)
                .withFechamento(fechamento)
                .withResponsavel("Joao")
                .withActived(true)
                .withCreatedAt(now)
                .withModifiedAt(now)
                .build();

        GetRestaurantOutput output = GetRestaurantOutput.from(restaurant);

        assertEquals("Restaurant X", output.nomeRestaurante());
        assertEquals("Italiano", output.tipo());
        assertEquals(endereco, output.endereco());
        assertEquals(abertura, output.abertura());
        assertTrue(output.actived());
        assertEquals("Joao", output.responsavel());
        assertEquals(now, output.createAt());
        assertEquals(now, output.modifiedAt());
    }

    @Test
    void deveCriarRestaurantAPartirDeGetRestaurantOutput() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        LocalDateTime abertura = LocalDateTime.of(2024, 2, 1, 9, 0);
        LocalDateTime fechamento = LocalDateTime.of(2024, 2, 1, 23, 0);
        var now = LocalDateTime.now();

        GetRestaurantOutput output = new GetRestaurantOutput(
                "Restaurant X",
                "Italiano",
                endereco,
                abertura,
                fechamento,
                "Joao",
                true,
                now,
                now
        );

        Restaurant restaurant = GetRestaurantOutput.to(output);

        assertEquals("Restaurant X", restaurant.getNomeRestaurante());
        assertEquals("Italiano", restaurant.getTipo());
        assertEquals(endereco, restaurant.getEndereco());
        assertEquals(abertura, restaurant.getAbertura());
        assertEquals(fechamento, restaurant.getFechamento());
        assertEquals("Joao", restaurant.getResponsavel());
        assertTrue(restaurant.isActived());
        assertEquals(now, restaurant.getCreateAt());
        assertEquals(now, restaurant.getModifiedAt());
    }

    @Test
    void deveDelegarCriacaoParaCreateRestaurantPorts() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        LocalDateTime abertura = LocalDateTime.of(2024, 2, 1, 9, 0);
        LocalDateTime fechamento = LocalDateTime.of(2024, 2, 1, 23, 0);
        var now = LocalDateTime.now();

        GetRestaurantOutput output = new GetRestaurantOutput(
                "Restaurant X",
                "Italiano",
                endereco,
                abertura,
                fechamento,
                "Joao",
                true,
                now,
                now
        );

        when(restaurantPorts.getRestaurant("Restaurant X"))
                .thenReturn(output);

        GetRestaurantOutput resultado =
                getRestaurant.getRestaurant("Restaurant X");

        assertEquals(output, resultado);

        verify(restaurantPorts, times(1))
                .getRestaurant("Restaurant X");
        verifyNoMoreInteractions(restaurantPorts);
    }
}
