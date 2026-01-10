package br.com.fiap.restaurant.application.domain.restaurant;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {
    private Restaurant restaurant;
    private Address endereco;
    private LocalDateTime abertura;
    private LocalDateTime fechamento;

    @BeforeEach
    void Setup(){
        endereco = new Address(
                "12345678",
                "Rua A",
                "Centro",
                "São Paulo"
        );

        abertura = LocalDateTime.of(2024, 1, 1, 8, 0);
        fechamento = LocalDateTime.of(2024, 1, 1, 22, 0);

        restaurant = new Restaurant.RestaurantBuilder()
                .withNomeRestaurant("Restaurante X")
                .withTipo("Italiano")
                .withEndereco(endereco)
                .withAbertura(abertura)
                .withFechamento(fechamento)
                .withResponsavel("João")
                .withActived(true)
                .withModifiedAt(LocalDateTime.now())
                .withCreatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void deveCriarRestaurantComBuilder() {

        assertNotNull(restaurant);
        assertEquals("Restaurante X", restaurant.getNomeRestaurante());
        assertEquals("Italiano", restaurant.getTipo());
        assertEquals(endereco, restaurant.getEndereco());
        assertEquals(abertura, restaurant.getAbertura());
        assertEquals(fechamento, restaurant.getFechamento());
        assertEquals("João", restaurant.getResponsavel());
        assertTrue(restaurant.isActived());
        assertNotNull(restaurant.getModifiedAt());
        assertNotNull(restaurant.getCreateAt());
    }

    @Test
    void deveAtualizarDadosDoRestaurant() {

        Address novoEndereco = new Address(
                "87654321",
                "Rua B",
                "Bairro B",
                "Rio de Janeiro"
        );

        LocalDateTime novaAbertura = LocalDateTime.of(2024, 2, 1, 9, 0);
        LocalDateTime novoFechamento = LocalDateTime.of(2024, 2, 1, 23, 0);

        restaurant.update(
                "Novo Nome",
                "Japones",
                novoEndereco,
                novaAbertura,
                novoFechamento
        );

        assertEquals("Novo Nome", restaurant.getNomeRestaurante());
        assertEquals("Japones", restaurant.getTipo());
        assertEquals(novoEndereco, restaurant.getEndereco());
        assertEquals(novaAbertura, restaurant.getAbertura());
        assertEquals(novoFechamento, restaurant.getFechamento());
    }

    @Test
    void deveDesativarRestaurantQuandoAtivo() {

        restaurant.delete();
        assertFalse(restaurant.isActived());
    }

    @Test
    void naoDeveDesativarRestaurantQuandoJaInativo() {

        restaurant.delete();
        restaurant.delete();
        assertFalse(restaurant.isActived());
    }

    @Test
    void deveAtualizarResponsavelQuandoAtivo() {

        restaurant.updateOwner("Maria");
        assertEquals("Maria", restaurant.getResponsavel());
    }

    @Test
    void deveAtualizarResponsavelQuandoInativo() {
        restaurant.delete();
        restaurant.updateOwner("Maria");
        assertFalse(restaurant.isActived());
    }

    @Test
    void deveCriarRestaurantComFactory() {
        RestaurantFactory factory = new DefaultRestaurantFactory();
        var restaurantFactory = factory.newRestaurant("Restaurante X", "Italiano", endereco, abertura, fechamento, "João");

        assertNotNull(restaurantFactory);
        assertEquals("Restaurante X", restaurantFactory.getNomeRestaurante());
        assertEquals("Italiano", restaurantFactory.getTipo());
        assertEquals(endereco, restaurantFactory.getEndereco());
        assertEquals(abertura, restaurantFactory.getAbertura());
        assertEquals(fechamento, restaurantFactory.getFechamento());
        assertEquals("João", restaurantFactory.getResponsavel());
        assertTrue(restaurantFactory.isActived());
        assertNotNull(restaurantFactory.getCreateAt());
    }

    @Test
    void naoDeveCriarRestaurantComFactoryNomeNull() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new DefaultRestaurantFactory().newRestaurant(null, "Italiano", endereco, abertura, fechamento, "João")
        );

        assertEquals("Nome do Restaurante Obrigatório", exception.getMessage());
    }

    @Test
    void naoDeveCriarRestaurantComFactoryResponsavelNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DefaultRestaurantFactory().newRestaurant("Restaurante X", "Italiano", endereco, abertura, fechamento, null)
        );

        assertEquals("Usuario não Existe!!", exception.getMessage());
    }
}
