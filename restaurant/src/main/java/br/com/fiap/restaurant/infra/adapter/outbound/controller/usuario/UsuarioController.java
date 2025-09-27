package br.com.fiap.restaurant.infra.adapter.outbound.controller.usuario;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.UsuarioPorts;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.create.CreateUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.create.CreateUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.delete.DeleteUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.delete.DeleteUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.get.GetUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.get.GetUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.list.ListUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.list.ListUsuarioOutput;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.update.UpdateUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.update.UpdateUsuarioOutput;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.inbound.CreateDTO;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.inbound.UpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(name = "/api/v1/usuario")
@CrossOrigin("*")
public class UsuarioController {

    private final CreateUsuario createUsuario;
    private final DeleteUsuario deleteUsuario;
    private final GetUsuario getUsuario;
    private final ListUsuario listUsuario;
    private final UpdateUsuario updateUsuario;

    public UsuarioController(CreateUsuario createUsuario, DeleteUsuario deleteUsuario, GetUsuario getUsuario, ListUsuario listUsuario, UpdateUsuario updateUsuario) {
        this.createUsuario = createUsuario;
        this.deleteUsuario = deleteUsuario;
        this.getUsuario = getUsuario;
        this.listUsuario = listUsuario;
        this.updateUsuario = updateUsuario;
    }

    @GetMapping("/number/{number}/page/{page}")
    public ResponseEntity<Pagination<Usuario>> listaUsuario (@PathVariable(value = "number") int number, @PathVariable(value = "page") int page) {
        Pagination<Usuario> usuarios = listUsuario
                .listUsuario(new Page(number, page))
                .mapItems(ListUsuarioOutput::to);

        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @PostMapping("/{username}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable(value = "username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(GetUsuarioOutput.to(getUsuario.getUsuario(username)));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable(value = "username") String username){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(DeleteUsuarioOutput.to(deleteUsuario.deleteUsuario(username)));
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> createUsuario(@RequestBody CreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateUsuarioOutput.to(createUsuario.createUsuario(createDTO.to(createDTO))));
    }

    @PutMapping("/{username}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable(value = "username") String username, @RequestBody UpdateDTO updateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(UpdateUsuarioOutput.to(updateUsuario.updateUsuario(updateDTO.to(updateDTO))));
    }

}
