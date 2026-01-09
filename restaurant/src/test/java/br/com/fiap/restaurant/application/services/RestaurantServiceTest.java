package br.com.fiap.restaurant.application.services;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.domain.restaurant.RestaurantFactory;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.outbound.restaurant.RestaurantRepository;
import br.com.fiap.restaurant.application.useCase.outbound.usuario.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {
    @Mock
    private RestaurantRepository repository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RestaurantFactory factory;

    @InjectMocks
    private RestaurantService service;

    private Restaurant restaurant;

    @BeforeEach
    void setup() {
        restaurant = mock(Restaurant.class);
    }

    @Test
    void deveDeletarRestaurante() {
        when(repository.findByNomeRestaurante("Rest A")).thenReturn(restaurant);
        when(repository.delete(restaurant)).thenReturn(restaurant);

        var output = service.deleteRestaurant("Rest A");

        assertNotNull(output);
        verify(repository).delete(restaurant);
    }

    @Test
    void deveBuscarRestaurantePorNome() {
        when(repository.findByNomeRestaurante("Rest A")).thenReturn(restaurant);

        var output = service.getRestaurant("Rest A");

        assertNotNull(output);
        verify(repository).findByNomeRestaurante("Rest A");
    }

    @Test
    void deveListarRestaurantesComPaginacao() {
        Page page = mock(Page.class);
        Pagination<Restaurant> pagination = mock(Pagination.class);

        when(repository.findAll(page)).thenReturn(pagination);
        when(pagination.mapItems(any())).thenReturn(mock(Pagination.class));

        var result = service.listRestaurant(page);

        assertNotNull(result);
    }

    @Test
    void deveAtualizarRestaurante() {
        UpdateRestaurantInput input = mock(UpdateRestaurantInput.class);

        when(input.nomeRestaurante()).thenReturn("Rest A");
        when(repository.findByNomeRestaurante("Rest A")).thenReturn(restaurant);
        when(repository.update(restaurant)).thenReturn(restaurant);

        var output = service.updateRestaurant(input);

        assertNotNull(output);
        verify(restaurant).update(any(), any(), any(), any(), any());
        verify(repository).update(restaurant);
    }

}
