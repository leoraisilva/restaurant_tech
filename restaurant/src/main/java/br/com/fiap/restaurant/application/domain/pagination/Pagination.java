package br.com.fiap.restaurant.application.domain.pagination;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Pagination<T> {
    private int number;
    private int page;
    private int total;
    private List<T> items;

    public Pagination(int number, int page, int total, List<T> items) {
        this.number = number;
        this.page = page;
        this.total = total;
        this.items = items;
    }

    public int getNumber() {
        return number;
    }

    public int getPage() {
        return page;
    }

    public int getTotal() {
        return total;
    }

    public List<T> getItems() {
        return items;
    }

    public <R> Pagination <R> mapItems(final Function<T, R> mapper) {
        return new Pagination<>(number, page, total, items.stream().map(mapper).collect(Collectors.toList()));
    }

}
