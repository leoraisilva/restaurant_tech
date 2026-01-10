package br.com.fiap.restaurant.application.useCase.inbound.cardapio;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.delete.DeleteCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.get.GetCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.get.GetCardapioOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetCardapioTest {
    @Mock
    private CardapioPorts cardapioPorts;
    @InjectMocks
    private GetCardapio getCardapio;

    @Test
    void deveCriarGetCardapioOutputAPartirDeCardapio() {
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

        GetCardapioOutput output = GetCardapioOutput.from(cardapio);

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
    void deveCriarCardapioAPartirDeGetCardapioOutput() {
        LocalDateTime now = LocalDateTime.now();

        GetCardapioOutput output = new GetCardapioOutput(
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

        Cardapio cardapio = GetCardapioOutput.to(output);

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
    void deveDelegarCriacaoParaGetCardapioPorts() {
        LocalDateTime now = LocalDateTime.of(2024, 1, 1, 12, 0);

        GetCardapioOutput outputEsperado = new GetCardapioOutput(
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

        when(cardapioPorts.getCardapio("Pizza"))
                .thenReturn(outputEsperado);

        GetCardapioOutput resultado =
                getCardapio.getCardapio("Pizza");

        assertEquals(outputEsperado, resultado);

        verify(cardapioPorts, times(1))
                .getCardapio("Pizza");
        verifyNoMoreInteractions(cardapioPorts);
    }
}
