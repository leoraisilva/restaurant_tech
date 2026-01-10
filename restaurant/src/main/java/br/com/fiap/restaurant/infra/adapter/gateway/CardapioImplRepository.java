package br.com.fiap.restaurant.infra.adapter.gateway;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.useCase.outbound.cardapio.CardapioRepository;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.entity.CardapioMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.entity.ICardapioMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.cardapio.CardapioEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.cardapio.CardapioJPARespository;
import org.springframework.data.domain.Pageable;

import java.util.stream.Collectors;

public class CardapioImplRepository implements CardapioRepository {
    private final ICardapioMapper mapper;
    private final CardapioJPARespository respository;

    public CardapioImplRepository(ICardapioMapper mapper, CardapioJPARespository respository) {
        this.mapper = mapper;
        this.respository = respository;
    }

    @Override
    public Cardapio create(Cardapio cardapio) {
        var cardapioEntity = mapper.toEntity(cardapio);
        respository.save(cardapioEntity);
        cardapio = mapper.toDomain(cardapioEntity);
        cardapio.update(cardapioEntity.getProduct(), cardapioEntity.getDescricao(), cardapioEntity.getPreco(), cardapioEntity.getImagem(), cardapioEntity.isEntrega());
        return cardapio;
    }

    @Override
    public Cardapio update(Cardapio cardapio) {
        var cardapioEntity = respository.findByProduct(cardapio.getProduct());
        if (cardapioEntity.isDisponivel()){
            cardapioEntity.setDescricao(cardapio.getDescricao());
            cardapioEntity.setPreco(cardapio.getPreco());
            cardapioEntity.setImagem(cardapio.getImagem());
            cardapioEntity.setEntrega(cardapio.isEntrega());
            respository.save(cardapioEntity);
        }
        return mapper.toDomain(cardapioEntity);
    }

    @Override
    public Cardapio findByProduct(String product) {
        var cardapioEntity = respository.findByProduct(product);
        if(cardapioEntity.isDisponivel())
            return mapper.toDomain(cardapioEntity);
        return null;
    }

    @Override
    public Pagination<Cardapio> findAll(Page page) {
        var withPage = Pageable.ofSize(page.page()).withPage(page.number() - 1);
        var pageResult = respository.findAll(withPage);
        return new Pagination<Cardapio>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalPages(),
                pageResult.getContent()
                        .stream()
                        .filter(CardapioEntity::isDisponivel)
                        .map(mapper::toDomain)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Cardapio delete(Cardapio cardapio) {
        var cardapioEntity = respository.findByProduct(cardapio.getProduct());
        if (cardapioEntity.isDisponivel()){
            cardapioEntity.setDisponivel(false);
            respository.save(cardapioEntity);
        }
        return mapper.toDomain(cardapioEntity);
    }
}
