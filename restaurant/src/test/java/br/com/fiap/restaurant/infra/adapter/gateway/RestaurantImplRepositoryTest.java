package br.com.fiap.restaurant.infra.adapter.gateway;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.entity.IRestaurantMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IAddressMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.restaurant.RestaurantEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.AddressEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.UsuarioEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.restaurant.RestaurantJPARepository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.AddressJPARepository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.UsuarioJPARepository;
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
    private UsuarioJPARepository usuarioRepository;

    @Mock
    private IRestaurantMapper restaurantMapper;

    @Mock
    private IAddressMapper addressMapper;

    @InjectMocks
    private RestaurantImplRepository repository;

    private Restaurant restaurant;
    private RestaurantEntity restaurantEntity;
    private AddressEntity addressEntity;
    private UsuarioEntity usuarioEntity;

    @BeforeEach
    void setup() {
        restaurant = mock(Restaurant.class);

        restaurantEntity = new RestaurantEntity();
        restaurantEntity.setNomeRestaurante("Restaurante A");
        restaurantEntity.setTipo("Japonesa");
        restaurantEntity.setCEP("12345000");
        restaurantEntity.setAbertura(LocalDateTime.of(5, 2,4, 10, 0));
        restaurantEntity.setFechamento(LocalDateTime.of(5, 2,4, 22, 0));
        restaurantEntity.setActived(true);

        addressEntity = new AddressEntity();
        addressEntity.setCEP("12345-000");

        usuarioEntity = new UsuarioEntity();
        usuarioEntity.setRegras(Role.OWNER);
    }

    @Test
    void deveCriarRestauranteComSucesso() {
        when(restaurant.getResponsavel()).thenReturn("admin");
        when(usuarioRepository.findByUsername("admin")).thenReturn(usuarioEntity);

        var endereco = mock(br.com.fiap.restaurant.application.domain.usuario.Address.class);
        when(restaurant.getEndereco()).thenReturn(endereco);
        when(endereco.CEP()).thenReturn("12345-000");

        when(addressRepository.findByCEP("12345-000")).thenReturn(addressEntity);
        when(restaurantMapper.toEntity(restaurant)).thenReturn(restaurantEntity);
        when(restaurantMapper.toDomain(restaurantEntity)).thenReturn(restaurant);
        when(addressMapper.toDomain(addressEntity)).thenReturn(endereco);

        Restaurant result = repository.create(restaurant);

        verify(restaurantRepository).save(restaurantEntity);
        assertNotNull(result);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioForCliente() {
        usuarioEntity.setRegras(Role.CLIENT);
        when(restaurant.getResponsavel()).thenReturn("cliente");
        when(usuarioRepository.findByUsername("cliente")).thenReturn(usuarioEntity);

        assertThrows(IllegalArgumentException.class,
                () -> repository.create(restaurant));
    }

    @Test
    void deveAtualizarRestauranteAtivo() {
        when(restaurant.getNomeRestaurante()).thenReturn("Restaurante A");
        when(restaurant.getTipo()).thenReturn("Italiana");
        when(restaurant.getAbertura()).thenReturn(LocalDateTime.of(5, 2,4, 10, 0));
        when(restaurant.getFechamento()).thenReturn(LocalDateTime.of(5, 2,4, 22, 0));

        var endereco = mock(br.com.fiap.restaurant.application.domain.usuario.Address.class);
        when(restaurant.getEndereco()).thenReturn(endereco);
        when(endereco.CEP()).thenReturn("12345-000");

        when(restaurantRepository.findByNomeRestaurante("Restaurante A"))
                .thenReturn(restaurantEntity);
        when(addressRepository.findByCEP("12345-000")).thenReturn(addressEntity);
        when(restaurantMapper.toDomain(restaurantEntity)).thenReturn(restaurant);
        when(addressMapper.toDomain(addressEntity)).thenReturn(endereco);

        Restaurant result = repository.update(restaurant);

        verify(restaurantRepository).save(restaurantEntity);
        assertNotNull(result);
    }

    @Test
    void deveBuscarRestauranteAtivo() {
        when(restaurantRepository.findByNomeRestaurante("Restaurante A"))
                .thenReturn(restaurantEntity);
        when(addressRepository.findByCEP("12345000")).thenReturn(addressEntity);
        when(restaurantMapper.toDomain(restaurantEntity)).thenReturn(restaurant);
        when(addressMapper.toDomain(addressEntity))
                .thenReturn(mock(br.com.fiap.restaurant.application.domain.usuario.Address.class));

        Restaurant result = repository.findByNomeRestaurante("Restaurante A");

        assertNotNull(result);
    }

    @Test
    void deveRetornarApenasRestaurantesAtivos() {
        Page page = new Page(1, 10);

        when(restaurantRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(restaurantEntity)));

        when(addressRepository.findByCEP("12345000")).thenReturn(addressEntity);
        when(restaurantMapper.toDomain(restaurantEntity)).thenReturn(restaurant);
        when(addressMapper.toDomain(addressEntity))
                .thenReturn(mock(br.com.fiap.restaurant.application.domain.usuario.Address.class));

        Pagination<Restaurant> result = repository.findAll(page);

        assertEquals(1, result.getItems().size());
    }

    @Test
    void deveDesativarRestaurante() {
        when(restaurant.getNomeRestaurante()).thenReturn("Restaurante A");
        when(restaurant.getEndereco())
                .thenReturn(mock(br.com.fiap.restaurant.application.domain.usuario.Address.class));
        when(restaurant.getEndereco().CEP()).thenReturn("12345000");

        when(restaurantRepository.findByNomeRestaurante("Restaurante A"))
                .thenReturn(restaurantEntity);
        when(addressRepository.findByCEP("12345000")).thenReturn(addressEntity);
        when(restaurantMapper.toDomain(restaurantEntity)).thenReturn(restaurant);
        when(addressMapper.toDomain(addressEntity))
                .thenReturn(mock(br.com.fiap.restaurant.application.domain.usuario.Address.class));

        Restaurant result = repository.delete(restaurant);

        assertFalse(restaurantEntity.isActived());
        verify(restaurantRepository).save(restaurantEntity);
        assertNotNull(result);
    }

}
