package com.example.conscot.Fragments_constructoras;

public class productos_constructoras_dialog {
    String precio,caracteristicas,Constructora;
    int cantidad;

    public productos_constructoras_dialog(String precio, String caracteristicas, int cantidad,String constructora) {
        this.precio = precio;
        this.caracteristicas = caracteristicas;
        this.cantidad=cantidad;
        this.Constructora=constructora;
    }

    public String getConstructora() {
        return Constructora;
    }

    public void setConstructora(String constructora) {
        Constructora = constructora;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
