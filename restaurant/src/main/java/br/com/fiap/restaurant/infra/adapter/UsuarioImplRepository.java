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
import java.util.stream.Collectors;

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
        var addressDomain = usuario.getEndereco();
        AddressEntity address = findByCEP(addressDomain.CEP());
        if(address == null)
            addressRepository.save(addressMapper.toEntity(addressDomain));
        UsuarioEntity usuarioEntity = usuarioMapper.toEntity(usuario);
        usuarioRepository.save(usuarioEntity);
        usuario = usuarioMapper.toDomain(usuarioEntity);
        usuario.update(usuario.getNome(), usuario.getUsername(), usuario.getEmail(), addressDomain, usuario.getNumero());
        return usuario;
    }

    @Override
    public Usuario findByUsername(String username) {
        var usuarioEntity =  usuarioRepository.findByUsername(username);
        var addressDomain = addressMapper.toDomain(addressRepository.findByCEP(usuarioEntity.getCEP()));
        if(usuarioEntity.isActived()) {
            var usuario = usuarioMapper.toDomain(usuarioRepository.findByUsername(username));
            usuario.update(usuario.getNome(), usuario.getUsername(), usuario.getEmail(), addressDomain, usuario.getNumero());
            return usuario;
        }
        return null;
    }

    @Override
    public Usuario update(Usuario usuario) {
        var usuarioEntity =  usuarioRepository.findByUsername(usuario.getUsername());
        var addressDomain = usuario.getEndereco();
        AddressEntity address = findByCEP(addressDomain.CEP());
        if(address == null)
            addressRepository.save(addressMapper.toEntity(addressDomain));
        if(usuarioEntity.isActived()){
            usuarioEntity.setUsername(usuario.getUsername());
            usuarioEntity.setNome(usuario.getNome());
            usuarioEntity.setRegras(usuario.getRegras());
            usuarioEntity.setModifiedAt(LocalDateTime.now());
            usuarioEntity.setCEP(addressDomain.CEP());
        }
        usuario = usuarioMapper.toDomain(usuarioRepository.save(usuarioEntity));
        usuario.update(usuario.getNome(), usuario.getUsername(), usuario.getEmail(), addressDomain, usuario.getNumero());
        return usuario;
    }

    @Override
    public Usuario delete(Usuario usuario) {
        var usuarioEntity =  usuarioRepository.findByUsername(usuario.getUsername());
        var addressDomain = usuario.getEndereco();
        if(usuarioEntity.isActived()){
            usuarioEntity.setActived(false);
            usuarioEntity.setModifiedAt(LocalDateTime.now());
        }
        usuario = usuarioMapper.toDomain(usuarioRepository.save(usuarioEntity)).delete(usuario.isActived());
        usuario.update(usuario.getNome(), usuario.getUsername(), usuario.getEmail(), addressDomain, usuario.getNumero());
        return usuario;

    }

    @Override
    public Pagination<Usuario> findAll(Page page) {
        var withPage = Pageable.ofSize(page.page()).withPage(page.number()-1);
        var pageResult = usuarioRepository.findAll(withPage);

        return  new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalPages(),
                pageResult.getContent()
                        .stream()
                        .filter(UsuarioEntity::isActived)
                        .map(usuarioEntity -> {
                                var address = addressRepository.findByCEP(usuarioEntity.getCEP());
                                var usuario = usuarioMapper.toDomain(usuarioEntity);
                            usuario.update(usuario.getNome(), usuario.getUsername(), usuario.getEmail(), addressMapper.toDomain(address), usuario.getNumero());
                                return usuario;
                            }
                        )
                        .collect(Collectors.toList())
        );
    }

    private AddressEntity findByCEP(String CEP) {
        return addressRepository.findByCEP(CEP);
    }
}
