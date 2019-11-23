package com.example.conscot.ui.Constructora;

public class descripcio_productos_dialog {
    String precio,caracteristicas;
    int cantidad;

    public descripcio_productos_dialog(String precio, String caracteristicas,int cantidad) {
        this.precio = precio;
        this.caracteristicas = caracteristicas;
        this.cantidad=cantidad;
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
