package br.com.fiap.restaurant.infra.adapter.outbound.controller.cardapio;

import br.com.fiap.restaurant.application.domain.cardapio.Cardapio;
import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.create.CreateCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.delete.DeleteCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.delete.DeleteCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.get.GetCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.get.GetCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.list.ListCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.list.ListCardapioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapio;
import br.com.fiap.restaurant.application.useCase.inbound.cardapio.update.UpdateCardapioOutput;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.inbound.CreateDTO;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.cardapio.inbound.UpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cardapio")
public class CardapioController {
    private final CreateCardapio createCardapio;
    private final DeleteCardapio deleteCardapio;
    private final UpdateCardapio updateCardapio;
    private final ListCardapio listCardapio;
    private final GetCardapio getCardapio;

    public CardapioController(CreateCardapio createCardapio, DeleteCardapio deleteCardapio, UpdateCardapio updateCardapio, ListCardapio listCardapio, GetCardapio getCardapio) {
        this.createCardapio = createCardapio;
        this.deleteCardapio = deleteCardapio;
        this.updateCardapio = updateCardapio;
        this.listCardapio = listCardapio;
        this.getCardapio = getCardapio;
    }

    @PostMapping("/create")
    public ResponseEntity<Cardapio> createCardapio(@RequestBody CreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateCardapioOutput.to(createCardapio.createCardapio(CreateDTO.from(createDTO))));
    }

    @PutMapping("/update")
    public ResponseEntity<Cardapio> updateCardapio(@RequestBody UpdateDTO updateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(UpdateCardapioOutput.to(updateCardapio.updateCardapio(UpdateDTO.from(updateDTO))));
    }

    @GetMapping("/search/{product}")
    public ResponseEntity<Cardapio> getCardapio(@PathVariable (value = "nomeProduto") String product) {
        return ResponseEntity.status(HttpStatus.OK).body(GetCardapioOutput.to(getCardapio.getCardapio(product)));
    }

    @GetMapping("/list/number/{number}/page/{page}")
    public ResponseEntity<Pagination<Cardapio>> listCardapio(@PathVariable (value = "number") int number, @PathVariable(value = "page") int page) {
        return ResponseEntity.status(HttpStatus.OK).body(listCardapio
                .listCardapio(new Page(number, page))
                .mapItems(ListCardapioOutput::to)
        );
    }

    @DeleteMapping("/delete/{product}")
    public ResponseEntity<Cardapio> deleteCardapio (@PathVariable (value = "product") String product) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(DeleteCardapioOutput.to(deleteCardapio.deleteCardapio(product)));
    }

}
