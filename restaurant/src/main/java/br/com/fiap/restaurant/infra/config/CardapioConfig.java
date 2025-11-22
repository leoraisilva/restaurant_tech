package br.com.fiap.restaurant.infra.config;

import br.com.fiap.restaurant.application.domain.cardapio.CardapioFactory;
import br.com.fiap.restaurant.application.domain.cardapio.DefaultCardapioFactory;
import br.com.fiap.restaurant.application.services.CardapioService;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.CardapioPorts;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.delete.DeleteCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.get.GetCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.list.ListCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapio;
import br.com.fiap.restaurant.application.useCase.outbound.cardapio.CardapioRepository;
import br.com.fiap.restaurant.infra.adapter.CardapioImplRepository;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.entity.CardapioMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.entity.ICardapioMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.cardapio.CardapioJPARespository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardapioConfig {
    @Bean
    CreateCardapio createCardapio(CardapioPorts cardapioPorts) {
        return new CreateCardapio(cardapioPorts);
    }
    @Bean
    DeleteCardapio deleteCardapio(CardapioPorts cardapioPorts) {
        return new DeleteCardapio(cardapioPorts);
    }
    @Bean
    UpdateCardapio updateCardapio(CardapioPorts cardapioPorts) {
        return new UpdateCardapio(cardapioPorts);
    }
    @Bean
    ListCardapio listCardapio(CardapioPorts cardapioPorts) {
        return new ListCardapio(cardapioPorts);
    }
    @Bean
    GetCardapio getCardapio(CardapioPorts cardapioPorts) {
        return new GetCardapio(cardapioPorts);
    }
    @Bean
    CardapioPorts cardapioService(CardapioFactory factory, CardapioRepository cardapioRepository) {
        return new CardapioService(factory, cardapioRepository);
    }
    @Bean
    CardapioFactory cardapioFactory () {
        return new DefaultCardapioFactory();
    }
    @Bean
    CardapioRepository cardapioRepository(ICardapioMapper mapper, CardapioJPARespository respository) {
        return new CardapioImplRepository(mapper, respository);
    }
    @Bean
    ICardapioMapper cardapioMapper(CardapioFactory cardapioFactory) {
        return new CardapioMapper(cardapioFactory);
    }

}
