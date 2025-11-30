package br.com.fiap.restaurant.infra.config;

import br.com.fiap.restaurant.application.domain.restaurant.DefaultRestaurantFactory;
import br.com.fiap.restaurant.application.domain.restaurant.RestaurantFactory;
import br.com.fiap.restaurant.application.domain.usuario.AddressFactory;
import br.com.fiap.restaurant.application.services.RestaurantService;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.RestaurantPorts;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.delete.DeleteRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.get.GetRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.list.ListRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurant;
import br.com.fiap.restaurant.application.useCase.outbound.restaurant.RestaurantRepository;
import br.com.fiap.restaurant.application.useCase.outbound.usuario.UsuarioRepository;
import br.com.fiap.restaurant.infra.adapter.RestaurantImplRepository;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.entity.IRestaurantMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.entity.RestaurantMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IAddressMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.restaurant.RestaurantJPARepository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.AddressJPARepository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.UsuarioJPARepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantConfig {

    @Bean
    CreateRestaurant createRestaurant(RestaurantPorts restaurantPorts) {
        return new CreateRestaurant(restaurantPorts);
    }
    @Bean
    DeleteRestaurant deleteRestaurant(RestaurantPorts restaurantPorts) {
        return new DeleteRestaurant(restaurantPorts);
    }
    @Bean
    GetRestaurant getRestaurant(RestaurantPorts restaurantPorts) {
        return new GetRestaurant(restaurantPorts);
    }
    @Bean
    ListRestaurant listRestaurant(RestaurantPorts restaurantPorts) {
        return new ListRestaurant(restaurantPorts);
    }
    @Bean
    UpdateRestaurant updateRestaurant(RestaurantPorts restaurantPorts) {
        return new UpdateRestaurant(restaurantPorts);
    }
    @Bean
    RestaurantPorts restaurantPorts(RestaurantRepository restaurantRepository, RestaurantFactory restaurantFactory, UsuarioRepository repository) {
        return new RestaurantService(restaurantRepository, restaurantFactory, repository);
    }
    @Bean
    RestaurantRepository restaurantRepository(RestaurantJPARepository restaurantRepository, AddressJPARepository addressRepository, IRestaurantMapper restaurantMapper, IAddressMapper addressMapper) {
        return new RestaurantImplRepository(restaurantRepository, addressRepository, restaurantMapper, addressMapper);
    }
    @Bean
    RestaurantFactory restaurantFactory() {
        return new DefaultRestaurantFactory();
    }
    @Bean
    IRestaurantMapper restaurantMapper(RestaurantFactory restaurantFactory, AddressFactory addressFactory) {
        return new RestaurantMapper(restaurantFactory, addressFactory);
    }

}
