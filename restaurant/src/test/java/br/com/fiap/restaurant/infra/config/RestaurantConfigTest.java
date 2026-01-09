package br.com.fiap.restaurant.infra.config;

import br.com.fiap.restaurant.application.domain.restaurant.RestaurantFactory;
import br.com.fiap.restaurant.application.domain.usuario.AddressFactory;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.RestaurantPorts;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.delete.DeleteRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.get.GetRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.list.ListRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurant;
import br.com.fiap.restaurant.application.useCase.outbound.restaurant.RestaurantRepository;
import br.com.fiap.restaurant.application.useCase.outbound.usuario.UsuarioRepository;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.entity.IRestaurantMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IAddressMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.restaurant.RestaurantJPARepository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.AddressJPARepository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.UsuarioJPARepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RestaurantConfig.class)
public class RestaurantConfigTest {

    @MockBean
    RestaurantJPARepository restaurantJPARepository;

    @MockBean
    AddressJPARepository addressJPARepository;

    @MockBean
    UsuarioJPARepository usuarioJPARepository;

    @MockBean
    UsuarioRepository usuarioRepository;

    @MockBean
    IAddressMapper addressMapper;

    @MockBean
    AddressFactory addressFactory;

    @Autowired
    CreateRestaurant createRestaurant;

    @Autowired
    DeleteRestaurant deleteRestaurant;

    @Autowired
    GetRestaurant getRestaurant;

    @Autowired
    ListRestaurant listRestaurant;

    @Autowired
    UpdateRestaurant updateRestaurant;

    @Autowired
    RestaurantPorts restaurantPorts;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantFactory restaurantFactory;

    @Autowired
    IRestaurantMapper restaurantMapper;

    @Test
    void deveCarregarContextoECriarTodosOsBeans() {
        assertThat(createRestaurant).isNotNull();
        assertThat(deleteRestaurant).isNotNull();
        assertThat(getRestaurant).isNotNull();
        assertThat(listRestaurant).isNotNull();
        assertThat(updateRestaurant).isNotNull();

        assertThat(restaurantPorts).isNotNull();
        assertThat(restaurantRepository).isNotNull();
        assertThat(restaurantFactory).isNotNull();
        assertThat(restaurantMapper).isNotNull();
    }
}
