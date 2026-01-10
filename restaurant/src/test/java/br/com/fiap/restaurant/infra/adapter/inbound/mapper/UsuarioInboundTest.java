package br.com.fiap.restaurant.infra.adapter.inbound.mapper;
import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.AddressFactory;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.domain.usuario.UsuarioFactory;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.change.ChangeUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.create.CreateUsuarioInput;
import br.com.fiap.restaurant.application.useCase.inbound.usuario.update.UpdateUsuarioInput;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.AddressMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.UsuarioMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.inbound.CreateDTO;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.inbound.LoginDTO;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.inbound.UpdateDTO;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.outbound.TokenOut;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.UsuarioEntity;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.AddressEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public class UsuarioInboundTest {
    private UsuarioFactory usuarioFactory;
    private AddressFactory addressFactory;
    private UsuarioMapper mapper;
    private final AddressMapper addressMapper = new AddressMapper();

    @BeforeEach
    void setUp() {
        usuarioFactory = mock(UsuarioFactory.class);
        addressFactory = mock(AddressFactory.class);
        mapper = new UsuarioMapper(usuarioFactory, addressFactory);
    }

    @Test
    void deveConverterEntityParaDomain() {
        UsuarioEntity entity = new UsuarioEntity(
                "João",
                "joao123",
                "senha123",
                "joao@email.com",
                Role.CLIENT,
                "12345678",
                100,
                true,
                null,
                null
        );

        Address address = mock(Address.class);
        Usuario usuarioDomain = mock(Usuario.class);

        when(addressFactory.newAddress("12345678")).thenReturn(address);
        when(usuarioFactory.newUsuario(
                "João",
                "joao123",
                "senha123",
                "joao@email.com",
                Role.CLIENT,
                address,
                100
        )).thenReturn(usuarioDomain);

        Usuario result = mapper.toDomain(entity);

        assertNotNull(result);
        assertEquals(usuarioDomain, result);

        verify(addressFactory, times(1)).newAddress("12345678");
        verify(usuarioFactory, times(1)).newUsuario(
                "João",
                "joao123",
                "senha123",
                "joao@email.com",
                Role.CLIENT,
                address,
                100
        );
    }

    @Test
    void deveConverterDomainParaEntity() {
        Address address = mock(Address.class);
        when(address.CEP()).thenReturn("98765432");

        Usuario usuario = mock(Usuario.class);
        when(usuario.getNome()).thenReturn("Maria");
        when(usuario.getUsername()).thenReturn("maria123");
        when(usuario.getSenha()).thenReturn("senha456");
        when(usuario.getEmail()).thenReturn("maria@email.com");
        when(usuario.getRegras()).thenReturn(Role.OWNER);
        when(usuario.getEndereco()).thenReturn(address);
        when(usuario.getNumero()).thenReturn(50);
        when(usuario.isActived()).thenReturn(true);

        UsuarioEntity entity = mapper.toEntity(usuario);

        assertNotNull(entity);
        assertEquals("Maria", entity.getNome());
        assertEquals("maria123", entity.getUsername());
        assertEquals("senha456", entity.getSenha());
        assertEquals("maria@email.com", entity.getEmail());
        assertEquals(Role.OWNER, entity.getRegras());
        assertEquals("98765432", entity.getCEP());
        assertEquals(50, entity.getNumero());
        assertTrue(entity.isActived());

        assertNotNull(entity.getCreatedAt());
        assertNotNull(entity.getModifiedAt());
    }

    @Test
    void deveConverterAddressDomainParaEntity() {
        Address address = new Address(
                "12345678",
                "Rua das Flores",
                "Centro",
                "São Paulo"
        );

        AddressEntity entity = addressMapper.toEntity(address);

        assertNotNull(entity);
        assertEquals("12345678", entity.getCEP());
        assertEquals("Rua das Flores", entity.getLogradouro());
        assertEquals("Centro", entity.getBairro());
        assertEquals("São Paulo", entity.getCidade());
    }

    @Test
    void deveConverterAddressEntityParaDomain() {
        AddressEntity entity = new AddressEntity(
                "98765432",
                "Av. Paulista",
                "Bela Vista",
                "São Paulo"
        );

        Address address = addressMapper.toDomain(entity);

        assertNotNull(address);
        assertEquals("98765432", address.CEP());
        assertEquals("Av. Paulista", address.logradouro());
        assertEquals("Bela Vista", address.bairro());
        assertEquals("São Paulo", address.cidade());
    }

    @Test
    void deveConverterCreateDTOParaCreateUsuarioInput() {
        Address endereco = new Address(
                "12345678",
                "Rua das Flores",
                "Centro",
                "São Paulo"
        );

        CreateDTO dto = new CreateDTO(
                "João",
                "joao123",
                "senha123",
                "joao@email.com",
                Role.CLIENT,
                endereco,
                100
        );

        CreateUsuarioInput input = dto.to(dto);

        assertNotNull(input);
        assertEquals("João", input.nome());
        assertEquals("joao123", input.username());
        assertEquals("senha123", input.senha());
        assertEquals("joao@email.com", input.email());
        assertEquals(Role.CLIENT, input.regras());
        assertEquals(endereco, input.endereco());
        assertEquals(100, input.numero());
    }

    @Test
    void deveConverterLoginDTOParaChangeUsuarioInput() {
        LoginDTO dto = new LoginDTO(
                "joao123",
                "senhaSegura"
        );

        ChangeUsuarioInput input = dto.to(dto);

        assertNotNull(input);
        assertEquals("joao123", input.username());
        assertEquals("senhaSegura", input.senha());
    }

    @Test
    void deveCriarUpdateUsuarioInputQuandoDTOForValido() {
        Address endereco = new Address(
                "12345678",
                "Rua das Flores",
                "Centro",
                "São Paulo"
        );

        UpdateDTO dto = new UpdateDTO(
                "João",
                "joao123",
                "joao@email.com",
                endereco,
                100
        );

        UpdateUsuarioInput input = dto.to(dto);

        assertNotNull(input);
        assertEquals("João", input.nome());
        assertEquals("joao123", input.username());
        assertEquals("joao@email.com", input.email());
        assertEquals(endereco, input.endereco());
        assertEquals(100, input.numero());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForNulo() {
        Address endereco = new Address(
                "12345678",
                "Rua",
                "Centro",
                "São Paulo"
        );

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new UpdateDTO(
                        null,
                        "user",
                        "email@email.com",
                        endereco,
                        10
                )
        );

        assertEquals("Nome Obrigatorio", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForVazio() {
        Address endereco = new Address(
                "12345678",
                "Rua",
                "Centro",
                "São Paulo"
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new UpdateDTO(
                        "   ",
                        "user",
                        "email@email.com",
                        endereco,
                        10
                )
        );

        assertEquals("Nome Obrigatorio", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoEmailForNulo() {
        Address endereco = new Address(
                "12345678",
                "Rua",
                "Centro",
                "São Paulo"
        );

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new UpdateDTO(
                        "João",
                        "user",
                        null,
                        endereco,
                        10
                )
        );

        assertEquals("E-mail Obrigatorio", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoEmailForVazio() {
        Address endereco = new Address(
                "12345678",
                "Rua",
                "Centro",
                "São Paulo"
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new UpdateDTO(
                        "João",
                        "user",
                        "   ",
                        endereco,
                        10
                )
        );

        assertEquals("E-mail Obrigatorio", exception.getMessage());
    }

    @Test
    void deveCriarTokenLogin() {
        TokenOut token = new TokenOut("value");

        assertNotNull(token);
        assertEquals("value", token.token());
    }

}
