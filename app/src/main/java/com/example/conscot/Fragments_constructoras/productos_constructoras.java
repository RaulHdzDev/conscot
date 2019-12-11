package com.example.conscot.Fragments_constructoras;

public class productos_constructoras  {
    String material,precio,constructoras;

    public productos_constructoras(String material, String precio, String constructoras) {
        this.material = material;
        this.precio = precio;
        this.constructoras=constructoras;
    }

    public String getConstructoras() {
        return constructoras;
    }

    public void setConstructoras(String constructoras) {
        this.constructoras = constructoras;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
