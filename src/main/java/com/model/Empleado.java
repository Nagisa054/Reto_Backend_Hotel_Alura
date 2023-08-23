package com.model;

public class Empleado {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer edad = 18;
    private String puesto;
    private String nombre_en_sistema;
    private String clave_en_sistema;


    public Empleado(Integer id, String nombre, String apellido, String nombre_sis, String clave_sis){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombre_en_sistema = nombre_sis;
        this.clave_en_sistema = clave_sis;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre_en_sistema() {
        return nombre_en_sistema;
    }

    public String getClave_en_sistema() {
        return clave_en_sistema;
    }


    @Override
    public String toString(){
        return String.format(
                "Empleado{\n\t" +
                        "id: %d,\n\t" +
                        "nombre: %s,\n\t" +
                        "apellido: %s,\n\t" +
                        "edad: %d,\n\t" +
                        "puesto: %s,\n\t" +
                        "nombre_en_sistema: %s,\n\t" +
                        "clave_en_sistema: %s\n"
                        + "}",
                this.id,
                this.nombre,
                this.apellido,
                this.edad,
                this.puesto,
                this.nombre_en_sistema,
                this.clave_en_sistema
        );
    }
}
