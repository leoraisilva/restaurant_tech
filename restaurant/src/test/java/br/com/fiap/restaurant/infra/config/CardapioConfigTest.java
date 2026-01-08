package br.com.fiap.restaurant.infra.config;


import br.com.fiap.restaurant.application.domain.cardapio.CardapioFactory;
import br.com.fiap.restaurant.application.domain.restaurant.RestaurantFactory;
import br.com.fiap.restaurant.application.domain.usuario.AddressFactory;
import br.com.fiap.restaurant.application.services.CardapioService;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.CardapioPorts;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.delete.DeleteCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.get.GetCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.list.ListCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapio;
import br.com.fiap.restaurant.application.useCase.outbound.cardapio.CardapioRepository;
import br.com.fiap.restaurant.application.useCase.outbound.restaurant.RestaurantRepository;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.entity.ICardapioMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.entity.IRestaurantMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.entity.RestaurantMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IAddressMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.cardapio.CardapioJPARespository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.restaurant.RestaurantJPARepository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.AddressJPARepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CardapioConfig.class)
public class CardapioConfigTest {
    @MockBean
    RestaurantRepository restaurantRepository;

    @MockBean
    CardapioJPARespository cardapioJPARespository;

    @MockBean
    RestaurantJPARepository restaurantJPARepository;

    @MockBean
    AddressJPARepository addressJPARepository;

    @MockBean
    IRestaurantMapper restaurantMapper;

    @MockBean
    IAddressMapper addressMapper;

    @MockBean
    RestaurantFactory restaurantFactory;

    @MockBean
    AddressFactory addressFactory;

    @Autowired
    CreateCardapio createCardapio;

    @Autowired
    DeleteCardapio deleteCardapio;

    @Autowired
    ListCardapio listCardapio;

    @Autowired
    UpdateCardapio updateCardapio;

    @Autowired
    GetCardapio getCardapio;

    @Autowired
    CardapioPorts cardapioPorts;

    @Autowired
    CardapioFactory cardapioFactory;

    @Autowired
    CardapioRepository cardapioRepository;

    @Autowired
    ICardapioMapper cardapioMapper;


    @Test
    void deveCarregarContextoECriarBeans() {
        assertThat(createCardapio).isNotNull();
        assertThat(deleteCardapio).isNotNull();
        assertThat(updateCardapio).isNotNull();
        assertThat(listCardapio).isNotNull();
        assertThat(getCardapio).isNotNull();
        assertThat(cardapioPorts).isNotNull();
        assertThat(cardapioFactory).isNotNull();

        assertThat(restaurantRepository).isNotNull();
        assertThat(restaurantMapper).isNotNull();
        assertThat(cardapioFactory).isNotNull();
        assertThat(cardapioMapper).isNotNull();
        assertThat(cardapioJPARespository).isNotNull();
        assertThat(addressMapper).isNotNull();
        assertThat(cardapioRepository).isNotNull();
    }
}
