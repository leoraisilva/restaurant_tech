package br.com.fiap.restaurant.infra.adapter.outbound.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExceptionTest {
    private GlobalExceptionHandle globalExceptionHandle;
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        globalExceptionHandle = new GlobalExceptionHandle();
        webRequest = mock(WebRequest.class);
    }

    @Test
    void deveTratarNotFoundException() {
        var exception = new RuntimeException();

        ResponseEntity<Object> response =
                globalExceptionHandle.handleNotFound(exception, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof ResponseException);

        var body = (ResponseException) response.getBody();
        assertEquals(HttpStatus.NOT_FOUND.value(), body.status());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), body.title());
    }

    @Test
    void deveTratarIllegalArgumentException() {
        // given
        var exception = new IllegalArgumentException("Argumento inválido");

        // when
        ResponseEntity<Object> response =
                globalExceptionHandle.handleIllegalArgument(exception, webRequest);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        ResponseException body = (ResponseException) response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.NOT_FOUND.value(), body.status());
        assertEquals("Argumento inválido", body.detail());
    }

    @Test
    void deveTratarNullPointerException() {
        var exception = new NullPointerException("Erro \"interno\" inesperado");
        when(webRequest.getDescription(true))
                .thenReturn("uri=/api/teste;client=127.0.0.1");

        ResponseEntity<Object> response =
                globalExceptionHandle.handleNullPoint(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        ResponseException body = (ResponseException) response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), body.status());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), body.title());
        assertEquals("/api/teste", body.instance());
    }

    @Test
    void deveTratarSQLException() {
        var exception = new RuntimeException("Erro no banco");
        when(webRequest.getDescription(true))
                .thenReturn("uri=/api/db;client=127.0.0.1");

        ResponseEntity<Object> response =
                globalExceptionHandle.handleSQLException(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        ResponseException body = (ResponseException) response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.status());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), body.title());
        assertEquals("Request Not Accept", body.detail());
        assertEquals("/api/db", body.instance());
    }

    @Test
    void deveCriarResponseExceptionComValoresCorretos() {
        String type = "java.lang.IllegalArgumentException";
        String title = "Bad Request";
        int status = 400;
        String detail = "Parâmetro inválido";
        String instance = "/api/teste";

        ResponseException response = new ResponseException(
                type, title, status, detail, instance
        );

        assertEquals(type, response.type());
        assertEquals(title, response.title());
        assertEquals(status, response.status());
        assertEquals(detail, response.detail());
        assertEquals(instance, response.instance());
    }

    @Test
    void deveCompararDoisResponseExceptionIguais() {
        ResponseException r1 = new ResponseException(
                "type", "title", 404, "detail", "/api/1"
        );

        ResponseException r2 = new ResponseException(
                "type", "title", 404, "detail", "/api/1"
        );

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }
    @Test
    void toStringDeveConterOsCampos() {
        ResponseException response = new ResponseException(
                "type", "title", 500, "erro", "/api"
        );

        String toString = response.toString();

        assertTrue(toString.contains("type"));
        assertTrue(toString.contains("title"));
        assertTrue(toString.contains("status"));
        assertTrue(toString.contains("detail"));
        assertTrue(toString.contains("instance"));
    }

}
