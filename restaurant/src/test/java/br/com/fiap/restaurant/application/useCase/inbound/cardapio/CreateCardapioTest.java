package br.com.fiap.restaurant.application.useCase.inbound.cardapio;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapioInput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapioOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCardapioTest {

    @Mock
    private CardapioPorts cardapioPorts;

    @InjectMocks
    private CreateCardapio createCardapio;


    @Test
    void deveCriarCreateCardapioInput() {
        var createCardapioInput =  new CreateCardapioInput("X Burger", "Hamburger", 20.00, ".jpg", true, "Restaurant X");

        assertNotNull(createCardapioInput);
        assertEquals("X Burger", createCardapioInput.product());
        assertEquals("Hamburger", createCardapioInput.descricao());
        assertEquals(20.00, createCardapioInput.preco());
        assertEquals(".jpg", createCardapioInput.imagem());
        assertTrue(createCardapioInput.entrega());
        assertEquals("Restaurant X", createCardapioInput.restaurant());
    }

    @Test
    void deveCriarCreateCardapioOutputAPartirDeCardapio() {
        LocalDateTime now = LocalDateTime.now();

        Cardapio cardapio = new Cardapio.CardapioBuilder()
                .withNomeProduto("Pizza")
                .withDescricao("Pizza de calabresa")
                .withPreco(39.90)
                .withImagem("pizza.png")
                .withEntrega(true)
                .withRestaurant("Pizzaria X")
                .withDisponivel(true)
                .withCreateAt(now)
                .withModifiedAt(now)
                .build();

        CreateCardapioOutput output = CreateCardapioOutput.from(cardapio);

        assertEquals("Pizza", output.product());
        assertEquals("Pizza de calabresa", output.descricao());
        assertEquals(39.90, output.preco());
        assertEquals("pizza.png", output.imagem());
        assertTrue(output.entrega());
        assertEquals("Pizzaria X", output.restaurant());
        assertTrue(output.disponivel());
        assertEquals(now, output.createdAt());
        assertEquals(now, output.modifiedAt());
    }

    @Test
    void deveCriarCardapioAPartirDeCreateCardapioOutput() {
        LocalDateTime now = LocalDateTime.now();

        CreateCardapioOutput output = new CreateCardapioOutput(
                "Hambúrguer",
                "Hambúrguer artesanal",
                29.90,
                "burger.png",
                false,
                "Lanchonete Y",
                true,
                now,
                now
        );

        Cardapio cardapio = CreateCardapioOutput.to(output);

        assertEquals("Hambúrguer", cardapio.getProduct());
        assertEquals("Hambúrguer artesanal", cardapio.getDescricao());
        assertEquals(29.90, cardapio.getPreco());
        assertEquals("burger.png", cardapio.getImagem());
        assertFalse(cardapio.isEntrega());
        assertEquals("Lanchonete Y", cardapio.getRestaurant());
        assertTrue(cardapio.isDisponivel());
        assertEquals(now, cardapio.getCreatedAt());
        assertEquals(now, cardapio.getModifiedAt());
    }

    @Test
    void deveDelegarCriacaoParaCreateCardapioPorts() {
        // given
        LocalDateTime now = LocalDateTime.of(2024, 1, 1, 12, 0);

        CreateCardapioInput input = new CreateCardapioInput(
                "Pizza",
                "Pizza de calabresa",
                39.90,
                "pizza.png",
                true,
                "Pizzaria X"
        );

        CreateCardapioOutput outputEsperado = new CreateCardapioOutput(
                "Pizza",
                "Pizza de calabresa",
                39.90,
                "pizza.png",
                true,
                "Pizzaria X",
                true,
                now,
                now
        );

        when(cardapioPorts.createCardapio(input))
                .thenReturn(outputEsperado);

        // when
        CreateCardapioOutput resultado =
                createCardapio.createCardapio(input);

        // then
        assertEquals(outputEsperado, resultado);

        verify(cardapioPorts, times(1))
                .createCardapio(input);
        verifyNoMoreInteractions(cardapioPorts);
    }

}
