package br.com.fiap.restaurant.infra.adapter.outbound.controller.usuario;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.change.ChangeUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.change.ChangeUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.delete.DeleteUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.delete.DeleteUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.get.GetUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.get.GetUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.list.ListUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.list.ListUsuarioOutput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.update.UpdateUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.update.UpdateUsuarioOutput;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.inbound.CreateDTO;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.inbound.LoginDTO;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.inbound.UpdateDTO;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.outbound.TokenOut;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.UsuarioEntity;
import br.com.fiap.restaurant.infra.config.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin("*")
public class UsuarioController {

    private final CreateUsuario createUsuario;
    private final DeleteUsuario deleteUsuario;
    private final GetUsuario getUsuario;
    private final ListUsuario listUsuario;
    private final UpdateUsuario updateUsuario;
    private final ChangeUsuario changeUsuario;
    private final AuthenticationManager manager;
    private final TokenService tokenService;


    public UsuarioController(CreateUsuario createUsuario, DeleteUsuario deleteUsuario, GetUsuario getUsuario, ListUsuario listUsuario, UpdateUsuario updateUsuario, ChangeUsuario changeUsuario, AuthenticationManager manager, TokenService tokenService) {
        this.createUsuario = createUsuario;
        this.deleteUsuario = deleteUsuario;
        this.getUsuario = getUsuario;
        this.listUsuario = listUsuario;
        this.updateUsuario = updateUsuario;
        this.changeUsuario = changeUsuario;
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<TokenOut> loginUsuario(@RequestBody LoginDTO login) {
        var user = new UsernamePasswordAuthenticationToken(login.username(), login.senha());
        var auth = manager.authenticate(user);
        var token = tokenService.GenerationToken((UsuarioEntity) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new TokenOut(token));
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> createUsuario(@RequestBody CreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateUsuarioOutput.to(createUsuario.createUsuario(createDTO.to(createDTO))));
    }

    @GetMapping("/list/number/{number}/page/{page}")
    public ResponseEntity<Pagination<Usuario>> listaUsuario (@PathVariable(value = "number") int number, @PathVariable(value = "page") int page) {
        Pagination<Usuario> usuarios = listUsuario
                .listUsuario(new Page(number, page))
                .mapItems(ListUsuarioOutput::to);

        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable(value = "username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(GetUsuarioOutput.to(getUsuario.getUsuario(username)));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable(value = "username") String username){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(DeleteUsuarioOutput.to(deleteUsuario.deleteUsuario(username)));
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable(value = "username") String username, @RequestBody UpdateDTO updateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(UpdateUsuarioOutput.to(updateUsuario.updateUsuario(updateDTO.to(updateDTO))));
    }

    @PutMapping("/reset")
    public ResponseEntity<Usuario> changeUsuario(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(ChangeUsuarioOutput.to(changeUsuario.changeUsuario(loginDTO.to(loginDTO))));
    }

}
