package com.example.conscot.ui;

public class Usuarios {
    private String usuario;
    private String contrasena;
    private String correo;
    private String telefono;

    public Usuarios() {
    }

    public Usuarios(String usuario, String correo, String telefono){
        this.usuario = usuario;
        this.correo = correo;
        this.telefono=telefono;
    }

    public Usuarios(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

