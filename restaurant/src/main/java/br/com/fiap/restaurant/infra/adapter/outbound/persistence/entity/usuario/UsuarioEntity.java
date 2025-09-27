package br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario;

import br.com.fiap.restaurant.application.domain.usuario.Address;
import br.com.fiap.restaurant.application.domain.usuario.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {
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
    @Column(name = "cep", unique = true, nullable = false)
    private String cep;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "actived", nullable = false)
    private boolean actived;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public UsuarioEntity(String idUsuario, String nome, String username, String senha, String email, Role regras, String cep, Integer numero , boolean actived, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.username = username;
        this.senha = senha;
        this.email = email;
        this.regras = regras;
        this.cep = cep;
        this.numero = numero;
        this.actived = actived;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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
}
