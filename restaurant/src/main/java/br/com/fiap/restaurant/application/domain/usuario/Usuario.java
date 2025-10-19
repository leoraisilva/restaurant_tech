package br.com.fiap.restaurant.application.domain.usuario;

import java.time.LocalDateTime;

public class Usuario {
    private String nome;
    private String username;
    private String senha;
    private String email;
    private Role regras;
    private Address endereco;
    private Integer numero;
    private boolean actived;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Usuario (UsuarioBuilder builder) {
        this.nome = builder.nome;
        this.username = builder.username;
        this.senha = builder.senha;
        this.email = builder.email;
        this.regras = builder.regras;
        this.endereco = builder.endereco;
        this.actived = builder.actived;
        this.createdAt = builder.createdAt;
        this.modifiedAt = builder.modifiedAt;
        this.numero = builder.numero;
    }

    public String getNome() {
        return nome;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public Role getRegras() {
        return regras;
    }

    public Address getEndereco() {
        return endereco;
    }
    public Integer getNumero() {
        return numero;
    }

    public boolean isActived() {
        return actived;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public Usuario update(String nome, String username, String email, Address endereco, Integer numero){
        this.nome = nome;
        this.username= username;
        this.email = email;
        this.endereco = endereco;
        this.numero = numero;
        this.modifiedAt = LocalDateTime.now();
        return this;
    }

    public Usuario updatePassword(String senha) {
        if(!actived)
            return this;
        this.senha = senha;
        return this;
    }

    public Usuario delete(boolean actived) {
        if(!actived)
            return this;
        else
            this.actived = false;
        return this;
    }

    public static class UsuarioBuilder {
        private String nome;
        private String username;
        private String senha;
        private String email;
        private Role regras;
        private Address endereco;
        private Integer numero;
        private boolean actived;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public UsuarioBuilder withNome(String nome) {
            this.nome = nome;
            return this;
        }

        public UsuarioBuilder withUsuario(String username) {
            this.username = username;
            return this;
        }

        public UsuarioBuilder withSenha(String senha) {
            this.senha = senha;
            return this;
        }

        public UsuarioBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UsuarioBuilder withRegras(Role regras) {
            this.regras = regras;
            return this;
        }

        public UsuarioBuilder withEndereco(Address endereco) {
            this.endereco = endereco;
            return this;
        }

        public UsuarioBuilder withNumero(Integer numero) {
            this.numero = numero;
            return this;
        }

        public UsuarioBuilder actived(boolean actived) {
            this.actived = actived;
            return this;
        }

        public UsuarioBuilder withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UsuarioBuilder withModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public Usuario build() {
            if (this.createdAt == null)
                this.createdAt = LocalDateTime.now();
            return new Usuario(this);
        }
    }
}
