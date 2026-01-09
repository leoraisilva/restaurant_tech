package br.com.fiap.restaurant.infra.adapter.gateway;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.entity.ICardapioMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.cardapio.CardapioEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.cardapio.CardapioJPARespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardapioImplRepositoryTest {

    @Mock
    private ICardapioMapper mapper;

    @Mock
    private CardapioJPARespository repository;

    @InjectMocks
    private CardapioImplRepository cardapioRepository;

    @Mock
    private Cardapio cardapio;

    @Mock
    private CardapioEntity cardapioEntity;

    private final String PRODUTO_NOME = "Pizza";



    @Test
    void deveCriarCardapio() {
        // Arrange
        when(mapper.toEntity(cardapio)).thenReturn(cardapioEntity);
        when(mapper.toDomain(cardapioEntity)).thenReturn(cardapio);

        when(cardapioEntity.getProduct()).thenReturn(PRODUTO_NOME);
        when(cardapioEntity.getDescricao()).thenReturn("Pizza Calabresa");
        when(cardapioEntity.getPreco()).thenReturn(10.00);
        when(cardapioEntity.getImagem()).thenReturn("img.png");
        when(cardapioEntity.isEntrega()).thenReturn(true);

        var result = cardapioRepository.create(cardapio);

        assertNotNull(result);
        verify(repository).save(cardapioEntity);
        verify(cardapio).update(PRODUTO_NOME, "Pizza Calabresa", 10.00, "img.png", true);
    }

    @Test
    void deveAtualizarCardapioQuandoDisponivel() {
        when(cardapio.getProduct()).thenReturn(PRODUTO_NOME);
        when(repository.findByProduct(PRODUTO_NOME)).thenReturn(cardapioEntity);
        when(mapper.toDomain(cardapioEntity)).thenReturn(cardapio);
        when(cardapio.isDisponivel()).thenReturn(true);

        when(mapper.toEntity(cardapio)).thenReturn(cardapioEntity);

        var result = cardapioRepository.update(cardapio);

        assertNotNull(result);
        verify(repository).save(cardapioEntity);
    }

    @Test
    void naoDeveAtualizarCardapioQuandoIndisponivel() {
        when(cardapio.getProduct()).thenReturn(PRODUTO_NOME);
        when(repository.findByProduct(PRODUTO_NOME)).thenReturn(cardapioEntity);
        when(mapper.toDomain(cardapioEntity)).thenReturn(cardapio);
        when(cardapio.isDisponivel()).thenReturn(false);

        var result = cardapioRepository.update(cardapio);

        assertNotNull(result);
        verify(repository, never()).save(any());
    }

    @Test
    void deveBuscarCardapioPorProdutoDisponivel() {
        when(repository.findByProduct(PRODUTO_NOME)).thenReturn(cardapioEntity);
        when(cardapioEntity.isDisponivel()).thenReturn(true);
        when(mapper.toDomain(cardapioEntity)).thenReturn(cardapio);

        var result = cardapioRepository.findByProduct(PRODUTO_NOME);

        assertNotNull(result);
        verify(mapper).toDomain(cardapioEntity);
    }

    @Test
    void deveRetornarNullQuandoProdutoIndisponivel() {
        when(repository.findByProduct(PRODUTO_NOME)).thenReturn(cardapioEntity);
        when(cardapioEntity.isDisponivel()).thenReturn(false);

        var result = cardapioRepository.findByProduct(PRODUTO_NOME);

        assertNull(result);
    }

    @Test
    void deveListarSomenteCardapiosDisponiveisComPaginacao() {
        Page pageRequest = new Page(1, 10);
        when(cardapioEntity.isDisponivel()).thenReturn(true);
        when(mapper.toDomain(cardapioEntity)).thenReturn(cardapio);

        var pageResult = new PageImpl<>(List.of(cardapioEntity));
        when(repository.findAll(any(Pageable.class))).thenReturn(pageResult);

        Pagination<Cardapio> result = cardapioRepository.findAll(pageRequest);

        assertNotNull(result);
    }

    @Test
    void deveExcluirCardapioQuandoDisponivel() {
        when(cardapio.getProduct()).thenReturn(PRODUTO_NOME);
        when(repository.findByProduct(PRODUTO_NOME)).thenReturn(cardapioEntity);
        when(mapper.toDomain(cardapioEntity)).thenReturn(cardapio);
        when(cardapio.isDisponivel()).thenReturn(true);

        when(mapper.toEntity(cardapio)).thenReturn(cardapioEntity);

        var result = cardapioRepository.delete(cardapio);

        assertNotNull(result);
        verify(cardapio).delete();
        verify(repository).save(cardapioEntity);
    }
}
