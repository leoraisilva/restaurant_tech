package br.com.fiap.restaurant.application.useCase.inbound.restaurant;

import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateRestaurantTest {
    @Mock
    private RestaurantPorts restaurantPorts;

    @InjectMocks
    private CreateRestaurant createRestaurant;

    @Test
    void deveCriarCreateRestaurantInput() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        LocalDateTime abertura = LocalDateTime.of(2024, 2, 1, 9, 0);
        LocalDateTime fechamento = LocalDateTime.of(2024, 2, 1, 23, 0);
        var input = new CreateRestaurantInput("Restaurant X", "Italiano", endereco, abertura, fechamento, "Joao");

        assertNotNull(input);
        assertEquals("Restaurant X", input.getNomeRestaurante() );
        assertEquals("Italiano", input.getTipo());
        assertEquals(endereco, input.getEndereco());
        assertEquals(abertura, input.getAbertura());
        assertEquals(fechamento, input.getFechamento());
        assertEquals("Joao", input.getResponsavel());
    }

    @Test
    void deveAlterarCreateRestaurantInput() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        LocalDateTime abertura = LocalDateTime.of(2024, 2, 1, 9, 0);
        LocalDateTime fechamento = LocalDateTime.of(2024, 2, 1, 23, 0);
        var input = new CreateRestaurantInput("Restaurant X", "Italiano", endereco, abertura, fechamento, "Joao");

        Address novoEndereco = new Address(
                "01001000",
                "Praça da Sé",
                "Sé",
                "São Paulo"
        );
        LocalDateTime novoAbertura = LocalDateTime.of(2025, 3, 2, 7, 0);
        LocalDateTime novoFechamento = LocalDateTime.of(2025, 3, 2, 23, 0);

        input.setNomeRestaurante("Novo Restaurant");
        input.setTipo("Frances");
        input.setEndereco(novoEndereco);
        input.setAbertura(novoAbertura);
        input.setFechamento(novoFechamento);
        input.setResponsavel("Jonh");

        assertNotNull(input);
        assertEquals("Novo Restaurant", input.getNomeRestaurante() );
        assertEquals("Frances", input.getTipo());
        assertEquals(novoEndereco, input.getEndereco());
        assertEquals(novoAbertura, input.getAbertura());
        assertEquals(novoFechamento, input.getFechamento());
        assertEquals("Jonh", input.getResponsavel());
    }

    @Test
    void deveCriarCreateRestaurantOutputAPartirDeRestaurant() {
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

        CreateRestaurantOutput output = CreateRestaurantOutput.from(restaurant);

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
    void deveCriarRestaurantAPartirDeCreateRestaurantOutput() {
        Address endereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        LocalDateTime abertura = LocalDateTime.of(2024, 2, 1, 9, 0);
        LocalDateTime fechamento = LocalDateTime.of(2024, 2, 1, 23, 0);
        var now = LocalDateTime.now();

        CreateRestaurantOutput output = new CreateRestaurantOutput(
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

        Restaurant restaurant = CreateRestaurantOutput.to(output);

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
        var input = new CreateRestaurantInput("Restaurant X", "Italiano", endereco, abertura, fechamento, "Joao");


        CreateRestaurantOutput output = new CreateRestaurantOutput(
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

        when(restaurantPorts.createRestaurant(input))
                .thenReturn(output);

        CreateRestaurantOutput resultado =
                createRestaurant.createRestaurant(input);

        assertEquals(output, resultado);

        verify(restaurantPorts, times(1))
                .createRestaurant(input);
        verifyNoMoreInteractions(restaurantPorts);
    }

}
