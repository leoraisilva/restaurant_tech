package br.com.fiap.restaurant.infra.config;

import br.com.fiap.restaurant.application.domain.usuario.AddressFactory;
import br.com.fiap.restaurant.application.domain.usuario.UsuarioFactory;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.UsuarioPorts;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.change.ChangeUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.delete.DeleteUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.get.GetUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.list.ListUsuario;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.update.UpdateUsuario;
import br.com.fiap.restaurant.application.useCase.outbound.usuario.UsuarioRepository;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IAddressMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IUsuarioMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UsuarioConfig.class)
public class UsuarioConfigTest {
    @MockBean
    UsuarioRepository usuarioRepository;
    @Autowired
    CreateUsuario createUsuario;

    @Autowired
    DeleteUsuario deleteUsuario;

    @Autowired
    UpdateUsuario updateUsuario;

    @Autowired
    GetUsuario getUsuario;

    @Autowired
    ListUsuario listUsuario;

    @Autowired
    ChangeUsuario changeUsuario;

    @Autowired
    UsuarioPorts usuarioPorts;

    @Autowired
    UsuarioFactory usuarioFactory;

    @Autowired
    AddressFactory addressFactory;

    @Autowired
    IUsuarioMapper usuarioMapper;

    @Autowired
    IAddressMapper addressMapper;

    @Test
    void deveCarregarContextoECriarTodosOsBeans() {
        assertThat(createUsuario).isNotNull();
        assertThat(deleteUsuario).isNotNull();
        assertThat(updateUsuario).isNotNull();
        assertThat(getUsuario).isNotNull();
        assertThat(listUsuario).isNotNull();
        assertThat(changeUsuario).isNotNull();

        assertThat(usuarioPorts).isNotNull();
        assertThat(usuarioFactory).isNotNull();
        assertThat(addressFactory).isNotNull();
        assertThat(usuarioMapper).isNotNull();
        assertThat(addressMapper).isNotNull();
    }
}
