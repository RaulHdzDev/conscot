package com.example.conscot.Construcciones.lista_materiales;

public class materiales_para_construrir  {
    String material;
    String cantidad;

    public materiales_para_construrir(String material, String cantidad) {
        this.material = material;
        this.cantidad = cantidad;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
