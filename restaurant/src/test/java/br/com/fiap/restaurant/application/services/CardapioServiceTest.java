package br.com.fiap.restaurant.application.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.domain.cardapio.CardapioFactory;
import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapioInput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapioInput;
import br.com.fiap.restaurant.application.useCase.outbound.cardapio.CardapioRepository;
import br.com.fiap.restaurant.application.useCase.outbound.restaurant.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CardapioServiceTest {
    @Mock
    private CardapioFactory factory;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CardapioRepository repository;


    @InjectMocks
    private CardapioService service;

    private Cardapio cardapio;
    private CreateCardapioInput createInput;

    @Test
    void deveCriarCardapio_quandoRestauranteNaoExiste() {
        cardapio = mock(Cardapio.class);

        createInput = mock(CreateCardapioInput.class);
        when(createInput.product()).thenReturn("Pizza");
        when(createInput.descricao()).thenReturn("Pizza de calabresa");
        when(createInput.preco()).thenReturn(35.0);
        when(createInput.imagem()).thenReturn("pizza.png");
        when(createInput.entrega()).thenReturn(true);
        when(createInput.restaurant()).thenReturn("Restaurante A");
        when(restaurantRepository.findByNomeRestaurante("Restaurante A"))
                .thenReturn(null);

        when(factory.newCardapio(
                anyString(), anyString(), anyDouble(), anyString(), anyBoolean(), isNull()
        )).thenReturn(cardapio);

        when(repository.create(cardapio)).thenReturn(cardapio);

        var output = service.createCardapio(createInput);

        assertNotNull(output);
        verify(factory).newCardapio(
                "Pizza",
                "Pizza de calabresa",
                35.0,
                "pizza.png",
                true,
                null
        );
        verify(repository).create(cardapio);
    }

    @Test
    void deveCriarCardapioquandoRestauranteExiste() {
        var restaurante = mock(Restaurant.class);
        cardapio = mock(Cardapio.class);

        createInput = mock(CreateCardapioInput.class);
        when(createInput.product()).thenReturn("Pizza");
        when(createInput.descricao()).thenReturn("Pizza de calabresa");
        when(createInput.preco()).thenReturn(35.0);
        when(createInput.imagem()).thenReturn("pizza.png");
        when(createInput.entrega()).thenReturn(true);
        when(createInput.restaurant()).thenReturn("Restaurante A");

        when(restaurantRepository.findByNomeRestaurante("Restaurante A"))
                .thenReturn(restaurante);

        when(factory.newCardapio(
                anyString(), anyString(), anyDouble(), anyString(), anyBoolean(), anyString()
        )).thenReturn(cardapio);

        when(repository.create(cardapio)).thenReturn(cardapio);

        var output = service.createCardapio(createInput);

        assertNotNull(output);
        verify(factory).newCardapio(
                "Pizza",
                "Pizza de calabresa",
                35.0,
                "pizza.png",
                true,
                "Restaurante A"
        );
    }

}
