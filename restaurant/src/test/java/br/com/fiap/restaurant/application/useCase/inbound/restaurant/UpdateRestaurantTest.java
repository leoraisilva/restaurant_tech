package br.com.fiap.restaurant.application.useCase.inbound.restaurant;

import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateRestaurantTest {
    @Mock
    private RestaurantPorts restaurantPorts;

    @InjectMocks
    private UpdateRestaurant updateRestaurant;

    @Test
    void deveCriarUpdateRestaurantInput() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        LocalDateTime abertura = LocalDateTime.of(2024, 2, 1, 9, 0);
        LocalDateTime fechamento = LocalDateTime.of(2024, 2, 1, 23, 0);
        var input = new UpdateRestaurantInput("Restaurant X", "Italiano", endereco, abertura, fechamento);

        assertNotNull(input);
        assertEquals("Restaurant X", input.nomeRestaurante() );
        assertEquals("Italiano", input.tipo());
        assertEquals(endereco, input.endereco());
        assertEquals(abertura, input.abertura());
        assertEquals(fechamento, input.fechamento());
    }

    @Test
    void deveCriarUpdateRestaurantOutputAPartirDeRestaurant() {
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

        UpdateRestaurantOutput output = UpdateRestaurantOutput.from(restaurant);

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
    void deveCriarRestaurantAPartirDeUpdateRestaurantOutput() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        LocalDateTime abertura = LocalDateTime.of(2024, 2, 1, 9, 0);
        LocalDateTime fechamento = LocalDateTime.of(2024, 2, 1, 23, 0);
        var now = LocalDateTime.now();

        UpdateRestaurantOutput output = new UpdateRestaurantOutput(
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

        Restaurant restaurant = UpdateRestaurantOutput.to(output);

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
    void deveDelegarCriacaoParaUpdateRestaurantPorts() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        LocalDateTime abertura = LocalDateTime.of(2024, 2, 1, 9, 0);
        LocalDateTime fechamento = LocalDateTime.of(2024, 2, 1, 23, 0);
        var now = LocalDateTime.now();
        var input = new UpdateRestaurantInput("Restaurant X", "Italiano", endereco, abertura, fechamento);


        UpdateRestaurantOutput output = new UpdateRestaurantOutput(
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

        when(restaurantPorts.updateRestaurant(input))
                .thenReturn(output);

        UpdateRestaurantOutput resultado =
                updateRestaurant.updateRestaurant(input);

        assertEquals(output, resultado);

        verify(restaurantPorts, times(1))
                .updateRestaurant(input);
        verifyNoMoreInteractions(restaurantPorts);
    }

}
