package br.com.fiap.restaurant.infra.config;

import br.com.fiap.restaurant.application.domain.usuario.*;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.UsuarioPorts;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.create.CreateUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.create.CreateUsuarioInput;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.delete.DeleteUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.get.GetUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.list.ListUsuario;
import br.com.fiap.restaurant.application.gateway.inbound.usuario.update.UpdateUsuario;
import br.com.fiap.restaurant.application.gateway.outbound.usuario.UsuarioRepository;
import br.com.fiap.restaurant.application.services.UsuarioService;
import br.com.fiap.restaurant.infra.adapter.UsuarioImplRepository;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.AddressMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IAddressMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IUsuarioMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.UsuarioMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.AddressJPARepository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.UsuarioJPARepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {

    @Bean
    CreateUsuario createUsuario(UsuarioPorts usuarioPorts){
        return new CreateUsuario(usuarioPorts);
    }

    @Bean
    DeleteUsuario deleteUsuario(UsuarioPorts usuarioPorts){
        return new DeleteUsuario(usuarioPorts);
    }

    @Bean
    UpdateUsuario updateUsuario(UsuarioPorts usuarioPorts){
        return new UpdateUsuario(usuarioPorts);
    }

    @Bean
    GetUsuario getUsuario(UsuarioPorts usuarioPorts){
        return new GetUsuario(usuarioPorts);
    }

    @Bean
    ListUsuario listUsuario(UsuarioPorts usuarioPorts){
        return new ListUsuario(usuarioPorts);
    }

    @Bean
    UsuarioPorts usuarioPorts(UsuarioRepository repository, UsuarioFactory factory) {
        return new UsuarioService(repository, factory);
    }

    @Bean
    IUsuarioMapper iUsuarioMapper (UsuarioFactory usuarioFactory, AddressFactory addressFactory){
        return new UsuarioMapper(usuarioFactory, addressFactory);
    }

    @Bean
    UsuarioFactory usuarioFactory(){
        return new DefaultUsuarioFactory();
    }

    @Bean
    AddressFactory addressFactory() {
        return new DefaultAddressFactory();
    }

    @Bean
    IAddressMapper iAddressMapper (){
        return new AddressMapper();
    }

}
