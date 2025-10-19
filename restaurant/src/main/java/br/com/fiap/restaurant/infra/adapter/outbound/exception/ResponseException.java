package br.com.fiap.restaurant.infra.adapter.outbound.exception;

public record ResponseException (String type, String title, int status, String detail, String instance) {

}
