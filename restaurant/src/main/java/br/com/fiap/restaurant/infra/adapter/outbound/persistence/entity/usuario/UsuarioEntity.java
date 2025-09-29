package br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idUsuario;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "senha", nullable = false)
    private String senha;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "regras", nullable = false)
    private Role regras;
    @Column(name = "cep", nullable = false)
    private String CEP;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "actived", nullable = false)
    private boolean actived;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public UsuarioEntity(String nome, String username, String senha, String email, Role regras, String CEP, Integer numero , boolean actived, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.nome = nome;
        this.username = username;
        this.senha = senha;
        this.email = email;
        this.regras = regras;
        this.CEP = CEP;
        this.numero = numero;
        this.actived = actived;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public UsuarioEntity () {}

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRegras() {
        return regras;
    }

    public void setRegras(Role regras) {
        this.regras = regras;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.regras.equals(Role.OWNER)) {
            return List.of(
                new SimpleGrantedAuthority("ROLE_OWNER")
            );
        }
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
