package br.com.fiap.restaurant.infra.adapter.outbound.controller.restaurant;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.restaurant.Restaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.create.CreateRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.delete.DeleteRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.delete.DeleteRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.get.GetRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.get.GetRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.list.ListRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.list.ListRestaurantOutput;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurant;
import br.com.fiap.restaurant.application.useCase.inbound.restaurant.update.UpdateRestaurantOutput;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.inbound.CreateDTO;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.restaurant.inbound.UpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {
    private final CreateRestaurant createRestaurant;
    private final DeleteRestaurant deleteRestaurant;
    private final GetRestaurant getRestaurant;
    private final ListRestaurant listRestaurant;
    private final UpdateRestaurant updateRestaurant;

    public RestaurantController(CreateRestaurant createRestaurant, DeleteRestaurant deleteRestaurant, GetRestaurant getRestaurant, ListRestaurant listRestaurant, UpdateRestaurant updateRestaurant) {
        this.createRestaurant = createRestaurant;
        this.deleteRestaurant = deleteRestaurant;
        this.getRestaurant = getRestaurant;
        this.listRestaurant = listRestaurant;
        this.updateRestaurant = updateRestaurant;
    }

    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateRestaurantOutput.to(createRestaurant.createRestaurant(CreateDTO.from(createDTO))));
    }

    @PutMapping("/update")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody UpdateDTO updateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(UpdateRestaurantOutput.to(updateRestaurant.updateRestaurant(UpdateDTO.from(updateDTO))));
    }

    @GetMapping("/search/{nomeRestaurante}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable (value = "nomeRestaurante") String nomeRestaurante) {
        return ResponseEntity.status(HttpStatus.OK).body(GetRestaurantOutput.to(getRestaurant.getRestaurant(nomeRestaurante)));
    }

    @GetMapping("/list/number/{number}/page/{page}")
    public ResponseEntity<Pagination<Restaurant>> listRestaurant (@PathVariable (value = "number") int number, @PathVariable (value = "page") int page) {
        return ResponseEntity.status(HttpStatus.OK).body(listRestaurant
                .listRestaurant(new Page(number, page))
                .mapItems(ListRestaurantOutput::to)
        );
    }

    @DeleteMapping("/delete/{nomeRestaurante}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable(value = "nomeRestaurante") String nomeRestaurante) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(DeleteRestaurantOutput.to(deleteRestaurant.deleteRestaurant(nomeRestaurante)));
    }

}
