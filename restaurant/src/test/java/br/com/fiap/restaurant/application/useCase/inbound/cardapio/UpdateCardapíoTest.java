package br.com.fiap.restaurant.application.useCase.inbound.cardapio;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapioInput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapioOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateCardapíoTest {

    @Mock
    private CardapioPorts cardapioPorts;

    @InjectMocks
    private UpdateCardapio updateCardapio;

    @Test
    void deveCriarUpdateCardapioInput() {
        var updateCardapioInput =  new UpdateCardapioInput("X Burger", "Hamburger", 20.00, ".jpg", true);

        assertNotNull(updateCardapioInput);
        assertEquals("X Burger", updateCardapioInput.product());
        assertEquals("Hamburger", updateCardapioInput.descricao());
        assertEquals(20.00, updateCardapioInput.preco());
        assertEquals(".jpg", updateCardapioInput.imagem());
        assertTrue(updateCardapioInput.entrega());
    }

    @Test
    void deveCriarUpdateCardapioOutputAPartirDeCardapio() {
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

        UpdateCardapioOutput output = UpdateCardapioOutput.from(cardapio);

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
    void deveCriarCardapioAPartirDeUpdateCardapioOutput() {
        LocalDateTime now = LocalDateTime.now();

        UpdateCardapioOutput output = new UpdateCardapioOutput(
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

        Cardapio cardapio = UpdateCardapioOutput.to(output);

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
    void deveDelegarCriacaoParaUpdateCardapioPorts() {
        LocalDateTime now = LocalDateTime.of(2024, 1, 1, 12, 0);

        UpdateCardapioInput input = new UpdateCardapioInput(
                "Pizza",
                "Pizza de calabresa",
                39.90,
                "pizza.png",
                true
        );

        UpdateCardapioOutput outputEsperado = new UpdateCardapioOutput(
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

        when(cardapioPorts.updateCardapio(input))
                .thenReturn(outputEsperado);

        UpdateCardapioOutput resultado =
                updateCardapio.updateCardapio(input);

        assertEquals(outputEsperado, resultado);

        verify(cardapioPorts, times(1))
                .updateCardapio(input);
        verifyNoMoreInteractions(cardapioPorts);
    }

}
