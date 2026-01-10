package br.com.fiap.restaurant.application.useCase.inbound.cardapio;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.delete.DeleteCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.delete.DeleteCardapioOutput;
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
public class DeleteCardapioTest {
    @Mock
    private CardapioPorts cardapioPorts;

    @InjectMocks
    private DeleteCardapio deleteCardapio;

    @Test
    void deveCriarDeleteCardapioOutputAPartirDeCardapio() {
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

        DeleteCardapioOutput output = DeleteCardapioOutput.from(cardapio);

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
    void deveCriarCardapioAPartirDeDeleteCardapioOutput() {
        LocalDateTime now = LocalDateTime.now();

        DeleteCardapioOutput output = new DeleteCardapioOutput(
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

        Cardapio cardapio = DeleteCardapioOutput.to(output);

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
    void deveDelegarCriacaoParaDeleteCardapioPorts() {
        LocalDateTime now = LocalDateTime.of(2024, 1, 1, 12, 0);

        DeleteCardapioOutput outputEsperado = new DeleteCardapioOutput(
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

        when(cardapioPorts.deleteCardapio("Pizza"))
                .thenReturn(outputEsperado);

        DeleteCardapioOutput resultado =
                deleteCardapio.deleteCardapio("Pizza");

        assertEquals(outputEsperado, resultado);

        verify(cardapioPorts, times(1))
                .deleteCardapio("Pizza");
        verifyNoMoreInteractions(cardapioPorts);
    }

}
