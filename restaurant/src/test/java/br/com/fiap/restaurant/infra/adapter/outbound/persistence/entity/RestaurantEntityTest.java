package br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity;

import java.time.LocalDateTime;

import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.restaurant.RestaurantEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class RestaurantEntityTest {

    @Test
    void deveCriarRestaurantEntityUsandoConstrutorCompleto() {
        LocalDateTime abertura = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime fechamento = LocalDateTime.of(2025, 1, 1, 22, 0);
        LocalDateTime agora = LocalDateTime.now();

        RestaurantEntity restaurant = new RestaurantEntity(
                "Restaurante do João",
                "Italiana",
                "12345-678",
                abertura,
                fechamento,
                "João Silva",
                true,
                agora,
                agora
        );

        assertEquals("Restaurante do João", restaurant.getNomeRestaurante());
        assertEquals("Italiana", restaurant.getTipo());
        assertEquals("12345-678", restaurant.getCEP());
        assertEquals(abertura, restaurant.getAbertura());
        assertEquals(fechamento, restaurant.getFechamento());
        assertEquals("João Silva", restaurant.getResponsavel());
        assertTrue(restaurant.isActived());
        assertEquals(agora, restaurant.getCreateAt());
        assertEquals(agora, restaurant.getModifiedAt());
    }

    @Test
    void devePermitirAlterarValoresUsandoSetters() {
        RestaurantEntity restaurant = new RestaurantEntity();
        LocalDateTime agora = LocalDateTime.now();

        restaurant.setRestaurantId("1");
        restaurant.setNomeRestaurante("Restaurante da Maria");
        restaurant.setTipo("Brasileira");
        restaurant.setCEP("98765-432");
        restaurant.setAbertura(agora);
        restaurant.setFechamento(agora.plusHours(8));
        restaurant.setResponsavel("Maria Souza");
        restaurant.setActived(false);
        restaurant.setCreateAt(agora);
        restaurant.setModifiedAt(agora.plusDays(1));

        assertEquals("1", restaurant.getRestaurantId());
        assertEquals("Restaurante da Maria", restaurant.getNomeRestaurante());
        assertEquals("Brasileira", restaurant.getTipo());
        assertEquals("98765-432", restaurant.getCEP());
        assertEquals(agora, restaurant.getAbertura());
        assertEquals(agora.plusHours(8), restaurant.getFechamento());
        assertEquals("Maria Souza", restaurant.getResponsavel());
        assertFalse(restaurant.isActived());
        assertEquals(agora, restaurant.getCreateAt());
        assertEquals(agora.plusDays(1), restaurant.getModifiedAt());
    }

    @Test
    void deveCriarRestaurantEntityComConstrutorVazio() {
        RestaurantEntity restaurant = new RestaurantEntity();

        assertNotNull(restaurant);
    }
}
