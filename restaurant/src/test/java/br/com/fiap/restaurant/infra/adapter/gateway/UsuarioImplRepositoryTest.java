package br.com.fiap.restaurant.infra.adapter.gateway;


import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IAddressMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IUsuarioMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.AddressEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.UsuarioEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.AddressJPARepository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.UsuarioJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UsuarioImplRepositoryTest {
    @Mock
    private UsuarioJPARepository usuarioRepository;

    @Mock
    private AddressJPARepository addressRepository;

    @Mock
    private IUsuarioMapper usuarioMapper;

    @Mock
    private IAddressMapper addressMapper;

    @InjectMocks
    private UsuarioImplRepository repository;

    private Usuario usuario;
    private UsuarioEntity usuarioEntity;
    private Address address;
    private AddressEntity addressEntity;

    @BeforeEach
    void setup() {
        usuario = mock(Usuario.class);
        usuarioEntity = mock(UsuarioEntity.class);
        address = mock(Address.class);
        addressEntity = mock(AddressEntity.class);
    }

    @Test
    void deveCriarUsuarioComEnderecoNovo() {
        when(usuario.getEndereco()).thenReturn(address);
        when(address.CEP()).thenReturn("12345-000");

        when(addressRepository.findByCEP("12345-000")).thenReturn(null);
        when(addressMapper.toEntity(address)).thenReturn(addressEntity);

        when(usuarioMapper.toEntity(usuario)).thenReturn(usuarioEntity);
        when(usuarioMapper.toDomain(usuarioEntity)).thenReturn(usuario);

        when(usuarioEntity.getSenha()).thenReturn("senha123");

        repository.create(usuario);

        verify(addressRepository).save(addressEntity);
        verify(usuarioRepository).save(usuarioEntity);
        verify(usuarioEntity).setSenha(any());
        verify(usuario).update(any(), any(), any(), any(), any());
    }

    @Test
    void deveBuscarUsuarioAtivoPorUsername() {
        when(usuarioRepository.findByUsername("leo")).thenReturn(usuarioEntity);
        when(usuarioEntity.isActived()).thenReturn(true);
        when(usuarioEntity.getCEP()).thenReturn("12345-000");

        when(addressRepository.findByCEP("12345-000")).thenReturn(addressEntity);
        when(addressMapper.toDomain(addressEntity)).thenReturn(address);
        when(usuarioMapper.toDomain(usuarioEntity)).thenReturn(usuario);

        var result = repository.findByUsername("leo");

        assertNotNull(result);
        verify(usuario).update(any(), any(), any(), any(), any());
    }

    @Test
    void deveRetornarNullQuandoUsuarioInativo() {
        when(usuarioRepository.findByUsername("leo")).thenReturn(usuarioEntity);
        when(usuarioEntity.isActived()).thenReturn(false);

        var result = repository.findByUsername("leo");

        assertNull(result);
    }

    @Test
    void deveAtualizarUsuarioAtivo() {
        when(usuario.getUsername()).thenReturn("leo");
        when(usuario.getEndereco()).thenReturn(address);
        when(address.CEP()).thenReturn("12345-000");

        when(usuarioRepository.findByUsername("leo")).thenReturn(usuarioEntity);
        when(usuarioEntity.isActived()).thenReturn(true);

        when(addressRepository.findByCEP("12345-000")).thenReturn(addressEntity);
        when(usuarioMapper.toDomain(usuarioEntity)).thenReturn(usuario);
        when(usuarioRepository.save(usuarioEntity)).thenReturn(usuarioEntity);

        repository.update(usuario);

        verify(usuarioEntity).setUsername(any());
        verify(usuarioEntity).setNome(any());
        verify(usuarioEntity).setRegras(any());
        verify(usuarioRepository).save(usuarioEntity);
    }


    @Test
    void deveListarSomenteUsuariosAtivos() {
        var page = new Page(1, 10);

        when(usuarioEntity.isActived()).thenReturn(true);
        when(usuarioEntity.getCEP()).thenReturn("12345-000");

        when(addressRepository.findByCEP("12345-000")).thenReturn(addressEntity);
        when(addressMapper.toDomain(addressEntity)).thenReturn(address);
        when(usuarioMapper.toDomain(usuarioEntity)).thenReturn(usuario);

        var pageResult = new PageImpl<>(List.of(usuarioEntity));
        when(usuarioRepository.findAll(any(Pageable.class))).thenReturn(pageResult);

        Pagination<Usuario> result = repository.findAll(page);

        assertEquals(1, result.getItems().size());
    }

}
