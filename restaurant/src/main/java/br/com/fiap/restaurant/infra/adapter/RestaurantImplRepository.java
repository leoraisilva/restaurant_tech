package br.com.fiap.restaurant.infra.adapter;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.useCase.outbound.restaurant.RestaurantRepository;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.entity.IRestaurantMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IAddressMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.restaurant.RestaurantEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.restaurant.RestaurantJPARepository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.AddressJPARepository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.UsuarioJPARepository;
import org.springframework.data.domain.Pageable;

import java.util.stream.Collectors;

public class RestaurantImplRepository implements RestaurantRepository {

    private final RestaurantJPARepository restaurantRepository;
    private final AddressJPARepository addressRepository;
    private final IRestaurantMapper restaurantMapper;
    private final IAddressMapper addressMapper;

    public RestaurantImplRepository(RestaurantJPARepository restaurantRepository, AddressJPARepository addressRepository, IRestaurantMapper restaurantMapper, IAddressMapper addressMapper) {
        this.restaurantRepository = restaurantRepository;
        this.addressRepository = addressRepository;
        this.restaurantMapper = restaurantMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        var enderecoRequest = restaurant.getEndereco();
        var enderecoBD = addressRepository.findByCEP(enderecoRequest.CEP());
        if(enderecoBD == null)
            addressRepository.save(addressMapper.toEntity(enderecoRequest));
        var restaurantEntity = restaurantMapper.toEntity(restaurant);
        restaurantRepository.save(restaurantEntity);
        restaurant = restaurantMapper.toDomain(restaurantEntity);
        restaurant.update(restaurant.getNomeRestaurante(), restaurant.getTipo(), addressMapper.toDomain(enderecoBD), restaurant.getAbertura(), restaurant.getFechamento());
        return restaurant;
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        var restaurantEntity = restaurantRepository.findByNomeRestaurante(restaurant.getNomeRestaurante());
        var enderecoRequest = restaurant.getEndereco();
        var enderecoBD = addressRepository.findByCEP(enderecoRequest.CEP());
        if(enderecoBD == null)
            addressRepository.save(addressMapper.toEntity(enderecoRequest));
        var restaurantDomain = restaurantMapper.toDomain(restaurantEntity);
        if(restaurantEntity.isActived()){
            restaurantDomain.update(
                    restaurant.getNomeRestaurante(),
                    restaurant.getTipo(),
                    enderecoRequest,
                    restaurant.getAbertura(),
                    restaurant.getFechamento()
            );
        }
        restaurantEntity = restaurantMapper.toEntity(restaurantDomain);
        restaurantRepository.save(restaurantEntity);
        restaurant = restaurantMapper.toDomain(restaurantEntity);
        restaurant.update(restaurant.getNomeRestaurante(), restaurant.getTipo(), addressMapper.toDomain(enderecoBD), restaurant.getAbertura(), restaurant.getFechamento());
        return restaurant;
    }

    @Override
    public Restaurant findByNomeRestaurante(String nomeRestaurante) {
        var restaurantEntity = restaurantRepository.findByNomeRestaurante(nomeRestaurante);
        var endereco = addressRepository.findByCEP(restaurantEntity.getCEP());
        var restaurant = restaurantMapper.toDomain(restaurantEntity);
        if(restaurant.isActived()){
            restaurant.update(restaurant.getNomeRestaurante(), restaurant.getTipo(), addressMapper.toDomain(endereco), restaurant.getAbertura(), restaurant.getFechamento());
            return restaurant;
        }
        return null;
    }

    @Override
    public Pagination<Restaurant> findAll(Page page) {
        var withPage = Pageable.ofSize(page.page()).withPage(page.number() - 1);
        var pageResult = restaurantRepository.findAll(withPage);
        return new Pagination<Restaurant>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalPages(),
                pageResult.getContent()
                        .stream()
                        .filter(RestaurantEntity::isActived)
                        .map(restaurantEntity -> {
                            var endereco = addressRepository.findByCEP(restaurantEntity.getCEP());
                            var restaurant = restaurantMapper.toDomain(restaurantEntity);
                            restaurant.update(restaurantEntity.getNomeRestaurante(), restaurantEntity.getTipo(), addressMapper.toDomain(endereco), restaurantEntity.getAbertura(), restaurantEntity.getFechamento());
                            return restaurant;
                        })
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Restaurant delete(Restaurant restaurant) {
        var restaurantEntity = restaurantRepository.findByNomeRestaurante(restaurant.getNomeRestaurante());
        var endereco = addressRepository.findByCEP(restaurant.getEndereco().CEP());
        var restaurantDomain = restaurantMapper.toDomain(restaurantEntity);
        if(restaurantDomain.isActived()){
            restaurantDomain.delete();
            restaurantDomain.update(restaurantDomain.getNomeRestaurante(), restaurantDomain.getTipo(), addressMapper.toDomain(endereco), restaurantDomain.getAbertura(), restaurantDomain.getFechamento());
            restaurantRepository.save(restaurantMapper.toEntity(restaurantDomain));
            return restaurantDomain;
        }
        restaurantDomain.update(restaurantDomain.getNomeRestaurante(), restaurantDomain.getTipo(), addressMapper.toDomain(endereco), restaurantDomain.getAbertura(), restaurantDomain.getFechamento());
        return restaurantDomain;
    }
}
