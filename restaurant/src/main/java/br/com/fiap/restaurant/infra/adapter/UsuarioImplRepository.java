package br.com.fiap.restaurant.infra.adapter;

import br.com.fiap.restaurant.application.domain.page.Page;
import br.com.fiap.restaurant.application.domain.pagination.Pagination;
import br.com.fiap.restaurant.application.domain.usuario.Usuario;
import br.com.fiap.restaurant.application.gateway.outbound.usuario.UsuarioRepository;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IAddressMapper;
import br.com.fiap.restaurant.infra.adapter.inbound.mapper.usuario.entity.IUsuarioMapper;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.AddressEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.UsuarioEntity;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.AddressJPARepository;
import br.com.fiap.restaurant.infra.adapter.outbound.persistence.repository.usuario.UsuarioJPARepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Repository
public class UsuarioImplRepository implements UsuarioRepository {

    private final UsuarioJPARepository usuarioRepository;
    private final AddressJPARepository addressRepository;
    private final IUsuarioMapper usuarioMapper;
    private final IAddressMapper addressMapper;

    public UsuarioImplRepository(UsuarioJPARepository usuarioRepository, AddressJPARepository addressRepository, IUsuarioMapper usuarioMapper,IAddressMapper addressMapper) {
        this.usuarioRepository = usuarioRepository;
        this.addressRepository = addressRepository;
        this.usuarioMapper = usuarioMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public Usuario create(Usuario usuario) {
        AddressEntity address = findByCEP(usuario.getEndereco().CEP());
        if(address.getCEP().isEmpty())
            addressRepository.save(addressMapper.toEntity(usuario.getEndereco()));
        UsuarioEntity usuarioEntity = usuarioMapper.toEntity(usuario);
        usuarioRepository.save(usuarioEntity);
        return usuarioMapper.toDomain(usuarioEntity);
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioMapper.toDomain(usuarioRepository.findByUsername(username));
    }

    @Override
    public Usuario update(Usuario usuario) {
        var usuarioEntity =  usuarioRepository.findByUsername(usuario.getUsername());
        AddressEntity address = findByCEP(usuario.getEndereco().CEP());
        if(address.getCEP().isEmpty())
            addressRepository.save(addressMapper.toEntity(usuario.getEndereco()));
        if(usuarioEntity.isActived()){
            usuarioEntity.setIdUsuario(usuario.getUsername());
            usuarioEntity.setNome(usuario.getNome());
            usuarioEntity.setRegras(usuario.getRegras());
            usuarioEntity.setModifiedAt(LocalDateTime.now());
            usuarioEntity.setCep(address.getCEP());
        }
        return usuarioMapper.toDomain(usuarioRepository.save(usuarioEntity));
    }

    @Override
    public Usuario delete(Usuario usuario) {
        var usuarioEntity =  usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioEntity.isActived()){
            usuarioEntity.setActived(false);
            usuarioEntity.setModifiedAt(LocalDateTime.now());
        }
        return usuarioMapper.toDomain(usuarioRepository.save(usuarioEntity));
    }

    @Override
    public Pagination<Usuario> findAll(Page page) {
        var withPage = Pageable.ofSize(page.page()).withPage(page.number());
        var pageResult = usuarioRepository.findAll(withPage);

        return  new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalPages(),
                pageResult.getContent()
                        .stream()
                        .map(usuarioMapper::toDomain)
                        .toList()
        );
    }

    private AddressEntity findByCEP(String CEP) {
        return addressRepository.findByCEP(CEP);
    }
}
