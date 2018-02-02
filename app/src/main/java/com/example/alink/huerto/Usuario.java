package com.example.alink.huerto;

/**
 * Created by Alink on 04-07-2016.
 */
public class Usuario {
    private String username;
    private String email;
    private String password;
    private int idUsuario;

    public Usuario(String username, String email, String password, int idUsuario) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){ return  this.password; }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
