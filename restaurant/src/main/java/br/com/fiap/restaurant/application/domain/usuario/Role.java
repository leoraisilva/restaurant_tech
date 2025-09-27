package br.com.fiap.restaurant.application.domain.usuario;

public enum Role {
    OWNER("OWNER"),
    CLIENT("CLIENT");
    private String role;
    Role (String role){
        this.role = role;
    }
}
