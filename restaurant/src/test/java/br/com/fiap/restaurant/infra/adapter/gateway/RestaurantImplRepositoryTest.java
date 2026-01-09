package br.com.fiap.restaurant.infra.adapter.gateway;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.entity.IRestaurantMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IAddressMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.restaurant.RestaurantEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.AddressEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.restaurant.RestaurantJPARepository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.AddressJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantImplRepositoryTest {

    @Mock
    private RestaurantJPARepository restaurantRepository;

    @Mock
    private AddressJPARepository addressRepository;

    @Mock
    private IRestaurantMapper restaurantMapper;

    @Mock
    private IAddressMapper addressMapper;

    @InjectMocks
    private RestaurantImplRepository repository;

    @Mock
    private Restaurant restaurant;

    @Mock
    private RestaurantEntity restaurantEntity;

    @Mock
    private Address address;

    @Mock
    private AddressEntity addressEntity;

    private final String NOME_REST = "Rest A";
    private final String CEP = "12345-000";

    @BeforeEach
    void setup() {
        // Mocks já inicializados pelo MockitoExtension
    }

    @Test
    void deveCriarRestauranteComEnderecoNovo() {
        // Arrange
        when(restaurant.getEndereco()).thenReturn(address);
        when(address.CEP()).thenReturn(CEP);
        when(addressRepository.findByCEP(CEP)).thenReturn(null);

        // Configurar mappers para não retornarem null
        when(addressMapper.toEntity(address)).thenReturn(addressEntity);
        when(restaurantMapper.toEntity(restaurant)).thenReturn(restaurantEntity);
        when(restaurantMapper.toDomain(restaurantEntity)).thenReturn(restaurant);

        // Act
        repository.create(restaurant);

        // Assert
        verify(addressRepository).save(addressEntity);
        verify(restaurantRepository).save(restaurantEntity);
    }

    @Test
    void deveAtualizarRestauranteAtivo() {
        // Arrange
        when(restaurant.getNomeRestaurante()).thenReturn(NOME_REST);
        when(restaurant.getEndereco()).thenReturn(address);
        when(address.CEP()).thenReturn(CEP);
        when(restaurantRepository.findByNomeRestaurante(NOME_REST)).thenReturn(restaurantEntity);
        when(addressRepository.findByCEP(CEP)).thenReturn(addressEntity);

        when(restaurantEntity.isActived()).thenReturn(true);
        when(restaurantMapper.toDomain(restaurantEntity)).thenReturn(restaurant);
        when(restaurantMapper.toEntity(restaurant)).thenReturn(restaurantEntity);

        // Act
        repository.update(restaurant);

        // Assert
        verify(restaurantRepository).save(restaurantEntity);
    }

    @Test
    void deveExcluirRestauranteAtivo() {
        // Arrange
        when(restaurant.getNomeRestaurante()).thenReturn(NOME_REST);
        when(restaurant.getEndereco()).thenReturn(address);
        when(address.CEP()).thenReturn(CEP);
        when(restaurantRepository.findByNomeRestaurante(NOME_REST)).thenReturn(restaurantEntity);

        when(restaurantMapper.toDomain(restaurantEntity)).thenReturn(restaurant);
        when(restaurant.isActived()).thenReturn(true);

        // Importante: configurar o mapper para o save não receber null
        when(restaurantMapper.toEntity(restaurant)).thenReturn(restaurantEntity);
        when(addressRepository.findByCEP(CEP)).thenReturn(addressEntity);
        when(addressMapper.toDomain(addressEntity)).thenReturn(address);

        // Act
        repository.delete(restaurant);

        // Assert
        verify(restaurant).delete();
        verify(restaurantRepository).save(restaurantEntity);
    }
}
