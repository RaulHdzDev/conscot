package com.example.conscot.tareas_components;

public class tareas_datos_rv {

    public String nom;
    public String tipo;
    public String desc;



    public tareas_datos_rv(String nombre_de_la_tarea, String tipo_de_tarea, String descripcion_de_la_tarea) {
        this.nom = nom;
        this.tipo = tipo;
        this.desc = desc;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
