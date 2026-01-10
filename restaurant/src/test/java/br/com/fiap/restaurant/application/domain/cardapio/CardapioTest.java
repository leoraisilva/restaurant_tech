package br.com.fiap.restaurant.application.domain.cardapio;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CardapioTest {
    private Cardapio cardapio;

    @BeforeEach
    void setup () {
        cardapio = new Cardapio.CardapioBuilder()
                .withNomeProduto("Feijoada")
                .withDescricao("prato")
                .withPreco(10.00)
                .withImagem(".jpg")
                .withEntrega(true)
                .withRestaurant("Restaurante X")
                .withDisponivel(true)
                .withCreateAt(LocalDateTime.now())
                .withModifiedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void deveCriarCardapioComBuilder () {

        assertEquals("Feijoada", cardapio.getProduct());
        assertEquals("prato", cardapio.getDescricao());
        assertEquals(10.00, cardapio.getPreco());
        assertEquals(".jpg", cardapio.getImagem());
        assertTrue(cardapio.isEntrega());
        assertEquals("Restaurante X", cardapio.getRestaurant());
        assertTrue(cardapio.isDisponivel());
        assertNotNull(cardapio.getModifiedAt());
        assertNotNull(cardapio.getCreatedAt());
    }

    @Test
    void deveAtualizarDadosDoCardapioAtivo() {

        cardapio.update(
                "Carne",
                "Suina",
                30.90,
                ".jpeg",
                false
        );

        assertEquals("Carne", cardapio.getProduct());
        assertEquals("Suina", cardapio.getDescricao());
        assertEquals(30.90, cardapio.getPreco());
        assertEquals(".jpeg", cardapio.getImagem());
        assertFalse(cardapio.isEntrega());
    }

    @Test
    void deveAtualizarDadosDoCardapioInativo() {

        cardapio.delete();
        cardapio.update(
                "cordeiro",
                "costela",
                23.32,
                ".png",
                true
        );

        assertFalse(cardapio.isDisponivel());
        assertNotEquals("Carne", cardapio.getProduct());
        assertNotEquals("Suina", cardapio.getDescricao());
        assertNotEquals(30.90, cardapio.getPreco());
        assertNotEquals(".jpeg", cardapio.getImagem());
    }

    @Test
    void deveDesativarCardapioQuandoAtivo() {

        cardapio.delete();
        assertFalse(cardapio.isDisponivel());
    }

    @Test
    void deveDesativarCardapioQuandoInativo() {

        cardapio.delete();
        cardapio.delete();
        assertFalse(cardapio.isDisponivel());
    }

    @Test
    void deveCriarCardapioComFactory() {
        CardapioFactory factory = new DefaultCardapioFactory();
        var cardapioFactory = factory.newCardapio("Feijoada","prato",10.00,".jpg",true,"Restaurante X");

        assertEquals("Feijoada", cardapioFactory.getProduct());
        assertEquals("prato", cardapioFactory.getDescricao());
        assertEquals(10.00, cardapioFactory.getPreco());
        assertEquals(".jpg", cardapioFactory.getImagem());
        assertTrue(cardapioFactory.isEntrega());
        assertEquals("Restaurante X", cardapioFactory.getRestaurant());
        assertTrue(cardapioFactory.isDisponivel());
        assertNotNull(cardapioFactory.getModifiedAt());
        assertNotNull(cardapioFactory.getCreatedAt());

    }

    @Test
    void naoDeveCriarCardapioProdutoNull() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new DefaultCardapioFactory().newCardapio(null,"prato",10.00,".jpg",true,"Restaurante X")
        );

        assertEquals("Produto Obrigatorio", exception.getMessage());
    }

    @Test
    void naoDeveCriarCardapioRestaurantNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DefaultCardapioFactory().newCardapio("Feijoada","prato",10.00,".jpg",true,null)
        );

        assertEquals("Restaurant não Encontrado!!", exception.getMessage());
    }

}
