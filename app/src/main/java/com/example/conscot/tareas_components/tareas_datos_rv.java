package com.example.conscot.tareas_components;

public class tareas_datos_rv {

    private String nom;
    private String tipo;
    private String desc;
    private String fecha;



    public tareas_datos_rv(String nombre_de_la_tarea, String tipo_de_tarea, String descripcion_de_la_tarea, String fecha) {
        this.nom = nombre_de_la_tarea;
        this.tipo = tipo_de_tarea;
        this.desc = descripcion_de_la_tarea;
        this.fecha = fecha;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public void setTipo(String tip) {
        this.tipo = tip;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
