package br.com.fiap.restaurant.application.useCase.inbound.cardapio;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.get.GetCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.list.ListCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.list.ListCardapioOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListCardapioTest {
    @Mock
    private CardapioPorts cardapioPorts;

    @InjectMocks
    private ListCardapio listCardapio;

    @Test
    void deveCriarListCardapioOutputAPartirDeCardapio() {
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

        ListCardapioOutput output = ListCardapioOutput.from(cardapio);

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
    void deveCriarCardapioAPartirDeListCardapioOutput() {
        LocalDateTime now = LocalDateTime.now();

        ListCardapioOutput output = new ListCardapioOutput(
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

        Cardapio cardapio = ListCardapioOutput.to(output);

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
    void deveDelegarListagemParaCardapioPorts() {
        ListCardapio listCardapio =
                new ListCardapio(cardapioPorts);

        Page page = mock(Page.class);
        Pagination<ListCardapioOutput> paginacao =
                mock(Pagination.class);

        when(cardapioPorts.listCardapio(page))
                .thenReturn(paginacao);

        Pagination<ListCardapioOutput> resultado =
                listCardapio.listCardapio(page);

        assertEquals(paginacao, resultado);
        verify(cardapioPorts, times(1))
                .listCardapio(page);
        verifyNoMoreInteractions(cardapioPorts);
    }
}
