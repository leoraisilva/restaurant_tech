package br.com.fiap.restaurant.infra.adapter.inbound.mapper;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.domain.cardapio.CardapioFactory;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapioInput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapioInput;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.entity.CardapioMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.inbound.CreateDTO;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.inbound.UpdateDTO;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.cardapio.CardapioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CardapioInboundTest {
    private CardapioFactory factory;
    private CardapioMapper mapper;

    @BeforeEach
    void setUp() {
        factory = mock(CardapioFactory.class);
        mapper = new CardapioMapper(factory);
    }

    @Test
    void deveConverterEntityParaDomain() {
        CardapioEntity entity = new CardapioEntity(
                "Pizza",
                "Pizza de calabresa",
                45.90,
                "pizza.png",
                true,
                "Restaurante A",
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Cardapio cardapioDomain = mock(Cardapio.class);

        when(factory.newCardapio(
                "Pizza",
                "Pizza de calabresa",
                45.90,
                "pizza.png",
                true,
                "Restaurante A"
        )).thenReturn(cardapioDomain);

        Cardapio result = mapper.toDomain(entity);

        assertNotNull(result);
        assertEquals(cardapioDomain, result);

        verify(factory, times(1)).newCardapio(
                "Pizza",
                "Pizza de calabresa",
                45.90,
                "pizza.png",
                true,
                "Restaurante A"
        );
    }

    @Test
    void deveConverterDomainParaEntity() {
        LocalDateTime agora = LocalDateTime.now();

        Cardapio cardapio = mock(Cardapio.class);
        when(cardapio.getProduct()).thenReturn("Hambúrguer");
        when(cardapio.getDescricao()).thenReturn("Hambúrguer artesanal");
        when(cardapio.getPreco()).thenReturn(32.50);
        when(cardapio.getImagem()).thenReturn("burger.png");
        when(cardapio.isEntrega()).thenReturn(false);
        when(cardapio.getRestaurant()).thenReturn("Restaurante B");
        when(cardapio.isDisponivel()).thenReturn(true);
        when(cardapio.getCreatedAt()).thenReturn(agora);
        when(cardapio.getModifiedAt()).thenReturn(agora);

        CardapioEntity entity = mapper.toEntity(cardapio);

        assertNotNull(entity);
        assertEquals("Hambúrguer", entity.getProduct());
        assertEquals("Hambúrguer artesanal", entity.getDescricao());
        assertEquals(32.50, entity.getPreco());
        assertEquals("burger.png", entity.getImagem());
        assertFalse(entity.isEntrega());
        assertEquals("Restaurante B", entity.getRestaurant());
        assertTrue(entity.isDisponivel());
        assertEquals(agora, entity.getCreateAd());
        assertEquals(agora, entity.getModifiedAt());
    }

    @Test
    void deveConverterCreateDTOParaCreateCardapioInput() {
        CreateDTO dto = new CreateDTO(
                "Pizza",
                "Pizza de calabresa",
                45.90,
                "pizza.png",
                true,
                "Restaurante A"
        );

        CreateCardapioInput input = CreateDTO.from(dto);

        assertNotNull(input);
        assertEquals("Pizza", input.product());
        assertEquals("Pizza de calabresa", input.descricao());
        assertEquals(45.90, input.preco());
        assertEquals("pizza.png", input.imagem());
        assertTrue(input.entrega());
        assertEquals("Restaurante A", input.restaurant());
    }

    @Test
    void deveConverterUpdateDTOParaUpdateCardapioInput() {
        UpdateDTO dto = new UpdateDTO(
                "Hambúrguer",
                "Hambúrguer artesanal",
                32.50,
                "burger.png",
                false
        );

        UpdateCardapioInput input = UpdateDTO.from(dto);

        assertNotNull(input);
        assertEquals("Hambúrguer", input.product());
        assertEquals("Hambúrguer artesanal", input.descricao());
        assertEquals(32.50, input.preco());
        assertEquals("burger.png", input.imagem());
        assertFalse(input.entrega());
    }
}
