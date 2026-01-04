package br.com.fiap.restaurant.application.domain.page;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PageTest {
    @Test
    void deveCriarPage() {
        var page = new Page(2, 3);

        assertEquals(2, page.number());
        assertEquals(3, page.page());
    }

    @Test
    void deveCriarPageNaoNull(){
        var page = new Page(2, 3);

        assertNotNull(page);
    }
}
