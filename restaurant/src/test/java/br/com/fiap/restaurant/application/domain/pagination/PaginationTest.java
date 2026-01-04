package br.com.fiap.restaurant.application.domain.pagination;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaginationTest {
    @Test
    void deveCriarPaginationCorretamente() {
        List<String> items = List.of("A", "B", "C");

        Pagination<String> pagination = new Pagination<>(
                10,
                1,
                30,
                items
        );

        assertEquals(10, pagination.getNumber());
        assertEquals(1, pagination.getPage());
        assertEquals(30, pagination.getTotal());
        assertEquals(items, pagination.getItems());
    }

    @Test
    void deveMapearItemsParaOutroTipo() {
        List<Integer> items = List.of(1, 2, 3);

        Pagination<Integer> pagination = new Pagination<>(
                3,
                0,
                3,
                items
        );

        Pagination<String> result = pagination.mapItems(Object::toString);

        assertNotNull(result);
        assertEquals(3, result.getNumber());
        assertEquals(0, result.getPage());
        assertEquals(3, result.getTotal());
        assertEquals(List.of("1", "2", "3"), result.getItems());
    }

    @Test
    void mapItemsNaoDeveAlterarPaginationOriginal() {
        List<Integer> items = List.of(1, 2);

        Pagination<Integer> pagination = new Pagination<>(
                2,
                1,
                2,
                items
        );

        pagination.mapItems(i -> i * 2);

        assertEquals(List.of(1, 2), pagination.getItems());
    }
}
