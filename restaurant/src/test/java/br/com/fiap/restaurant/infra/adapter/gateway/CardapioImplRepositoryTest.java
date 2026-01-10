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
        cardapio = mock(Cardapio.class);

        cardapioEntity = new CardapioEntity();
        cardapioEntity.setProduct("Pizza");
        cardapioEntity.setDescricao("Pizza Calabresa");
        cardapioEntity.setPreco(50.0);
        cardapioEntity.setDisponivel(true);

        when(cardapio.getProduct()).thenReturn("Pizza");
        when(cardapio.getDescricao()).thenReturn("Nova descrição");
        when(cardapio.getPreco()).thenReturn(60.0);
        when(cardapio.getImagem()).thenReturn("img.png");
        when(cardapio.isEntrega()).thenReturn(true);

        when(repository.findByProduct("Pizza")).thenReturn(cardapioEntity);
        when(mapper.toDomain(cardapioEntity)).thenReturn(cardapio);

        Cardapio result = cardapioRepository.update(cardapio);

        verify(repository).save(cardapioEntity);
        assertEquals(cardapio, result);
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
    void deveMarcarCardapioComoIndisponivel() {
        cardapio = mock(Cardapio.class);

        cardapioEntity = new CardapioEntity();
        cardapioEntity.setProduct("Pizza");
        cardapioEntity.setDescricao("Pizza Calabresa");
        cardapioEntity.setPreco(50.0);
        cardapioEntity.setDisponivel(true);

        when(cardapio.getProduct()).thenReturn("Pizza");
        when(repository.findByProduct("Pizza")).thenReturn(cardapioEntity);
        when(mapper.toDomain(cardapioEntity)).thenReturn(cardapio);

        Cardapio result = cardapioRepository.delete(cardapio);

        assertFalse(cardapioEntity.isDisponivel());
        verify(repository).save(cardapioEntity);
        assertEquals(cardapio, result);
    }

}
