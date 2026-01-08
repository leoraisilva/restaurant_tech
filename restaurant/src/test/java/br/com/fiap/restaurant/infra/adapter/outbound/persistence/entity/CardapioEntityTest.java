package br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity;

import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.cardapio.CardapioEntity;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CardapioEntityTest {
    @Test
    void deveCriarCardapioEntityUsandoConstrutorCompleto() {
        LocalDateTime agora = LocalDateTime.now();

        CardapioEntity entity = new CardapioEntity(
                "Pizza",
                "Pizza de calabresa",
                45.90,
                "pizza.png",
                true,
                "Restaurante A",
                true,
                agora,
                agora
        );

        assertEquals("Pizza", entity.getProduct());
        assertEquals("Pizza de calabresa", entity.getDescricao());
        assertEquals(45.90, entity.getPreco());
        assertEquals("pizza.png", entity.getImagem());
        assertTrue(entity.isEntrega());
        assertEquals("Restaurante A", entity.getRestaurant());
        assertTrue(entity.isDisponivel());
        assertEquals(agora, entity.getCreateAd());
        assertEquals(agora, entity.getModifiedAt());
    }

    @Test
    void devePermitirAlterarValoresUsandoSetters() {
        CardapioEntity entity = new CardapioEntity();
        LocalDateTime agora = LocalDateTime.now();

        entity.setCardapioId("123");
        entity.setProduct("Hambúrguer");
        entity.setDescricao("Hambúrguer artesanal");
        entity.setPreco(32.50);
        entity.setImagem("burger.png");
        entity.setEntrega(false);
        entity.setRestaurant("Restaurante B");
        entity.setDisponivel(false);
        entity.setCreateAd(agora);
        entity.setModifiedAt(agora.plusHours(1));

        assertEquals("123", entity.getCardapioId());
        assertEquals("Hambúrguer", entity.getProduct());
        assertEquals("Hambúrguer artesanal", entity.getDescricao());
        assertEquals(32.50, entity.getPreco());
        assertEquals("burger.png", entity.getImagem());
        assertFalse(entity.isEntrega());
        assertEquals("Restaurante B", entity.getRestaurant());
        assertFalse(entity.isDisponivel());
        assertEquals(agora, entity.getCreateAd());
        assertEquals(agora.plusHours(1), entity.getModifiedAt());
    }

    @Test
    void deveCriarEntidadeComConstrutorVazio() {
        CardapioEntity entity = new CardapioEntity();

        assertNotNull(entity);
    }
}
