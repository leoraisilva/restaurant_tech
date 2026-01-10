package br.com.fiap.restaurant.infra.adapter.inbound.mapper;
import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.domain.restaurant.RestaurantFactory;
import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.AddressFactory;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantInput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantInput;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.entity.RestaurantMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.inbound.CreateDTO;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.inbound.UpdateDTO;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.restaurant.RestaurantEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantInboundTest {
    private RestaurantFactory restaurantFactory;
    private AddressFactory addressFactory;
    private RestaurantMapper mapper;

    @BeforeEach
    void setUp() {
        restaurantFactory = mock(RestaurantFactory.class);
        addressFactory = mock(AddressFactory.class);
        mapper = new RestaurantMapper(restaurantFactory, addressFactory);
    }

    @Test
    void deveConverterEntityParaDomain() {
        LocalDateTime abertura = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime fechamento = LocalDateTime.of(2025, 1, 1, 22, 0);

        RestaurantEntity entity = new RestaurantEntity(
                "Restaurante do João",
                "Italiana",
                "12345678",
                abertura,
                fechamento,
                "João Silva",
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Address address = mock(Address.class);
        Restaurant restaurantDomain = mock(Restaurant.class);

        when(addressFactory.newAddress("12345678")).thenReturn(address);
        when(restaurantFactory.newRestaurant(
                "Restaurante do João",
                "Italiana",
                address,
                abertura,
                fechamento,
                "João Silva"
        )).thenReturn(restaurantDomain);

        Restaurant result = mapper.toDomain(entity);

        assertNotNull(result);
        assertEquals(restaurantDomain, result);

        verify(addressFactory, times(1)).newAddress("12345678");
        verify(restaurantFactory, times(1)).newRestaurant(
                "Restaurante do João",
                "Italiana",
                address,
                abertura,
                fechamento,
                "João Silva"
        );
    }

    @Test
    void deveConverterDomainParaEntity() {
        LocalDateTime abertura = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime fechamento = LocalDateTime.of(2025, 1, 1, 22, 0);
        LocalDateTime agora = LocalDateTime.now();

        Address address = mock(Address.class);
        when(address.CEP()).thenReturn("98765-432");

        Restaurant restaurant = mock(Restaurant.class);
        when(restaurant.getNomeRestaurante()).thenReturn("Restaurante da Maria");
        when(restaurant.getTipo()).thenReturn("Brasileira");
        when(restaurant.getEndereco()).thenReturn(address);
        when(restaurant.getAbertura()).thenReturn(abertura);
        when(restaurant.getFechamento()).thenReturn(fechamento);
        when(restaurant.getResponsavel()).thenReturn("Maria Souza");
        when(restaurant.isActived()).thenReturn(true);
        when(restaurant.getCreateAt()).thenReturn(agora);
        when(restaurant.getModifiedAt()).thenReturn(agora);

        RestaurantEntity entity = mapper.toEntity(restaurant);

        assertNotNull(entity);
        assertEquals("Restaurante da Maria", entity.getNomeRestaurante());
        assertEquals("Brasileira", entity.getTipo());
        assertEquals("98765-432", entity.getCEP());
        assertEquals(abertura, entity.getAbertura());
        assertEquals(fechamento, entity.getFechamento());
        assertEquals("Maria Souza", entity.getResponsavel());
        assertTrue(entity.isActived());
        assertEquals(agora, entity.getCreateAt());
        assertEquals(agora, entity.getModifiedAt());
    }

    @Test
    void deveConverterCreateDTOParaCreateRestaurantInput() {
        Address endereco = new Address(
                "12345678",
                "Rua das Flores",
                "Centro",
                "São Paulo"
        );

        LocalDateTime abertura = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime fechamento = LocalDateTime.of(2025, 1, 1, 22, 0);

        CreateDTO dto = new CreateDTO(
                "Restaurante do João",
                "Italiana",
                endereco,
                abertura,
                fechamento,
                "João Silva"
        );

        CreateRestaurantInput input = CreateDTO.from(dto);

        assertNotNull(input);
        assertEquals("Restaurante do João", input.getNomeRestaurante());
        assertEquals("Italiana", input.getTipo());
        assertEquals(endereco, input.getEndereco());
        assertEquals(abertura, input.getAbertura());
        assertEquals(fechamento, input.getFechamento());
        assertEquals("João Silva", input.getResponsavel());
    }

    @Test
    void deveConverterUpdateDTOParaUpdateRestaurantInput() {
        Address endereco = new Address(
                "98765432",
                "Av. Paulista",
                "Bela Vista",
                "São Paulo"
        );

        LocalDateTime abertura = LocalDateTime.of(2025, 1, 2, 9, 0);
        LocalDateTime fechamento = LocalDateTime.of(2025, 1, 2, 23, 0);

        UpdateDTO dto = new UpdateDTO(
                "Restaurante da Maria",
                "Brasileira",
                endereco,
                abertura,
                fechamento
        );

        UpdateRestaurantInput input = UpdateDTO.from(dto);

        assertNotNull(input);
        assertEquals("Restaurante da Maria", input.nomeRestaurante());
        assertEquals("Brasileira", input.tipo());
        assertEquals(endereco, input.endereco());
        assertEquals(abertura, input.abertura());
        assertEquals(fechamento, input.fechamento());
    }

}
