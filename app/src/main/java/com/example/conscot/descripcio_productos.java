package com.example.conscot;

public class descripcio_productos {
    String precio,caracteristicas;

    public descripcio_productos(String precio, String caracteristicas) {
        this.precio = precio;
        this.caracteristicas = caracteristicas;
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
