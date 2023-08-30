package com.model;

/**
 * Esta clase representa la tabla Huespedes de la base de datos.
 *
 * @since 30/08/2023
 * @author Juan Pablo Rojas Cardozo | Nagisa054
 */
public class Huespedes {

    private Integer id;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    private Integer reservaId;

    /**
     *
     * @param nombre
     * @param apellido
     * @param fechaNacimiento
     * @param nacionalidad
     * @param telefono
     * @param reservaId
     */
    public Huespedes(String nombre, String apellido, String fechaNacimiento,
                     String nacionalidad, String telefono, Integer reservaId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.reservaId = reservaId;
    }

    //GETERS
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public Integer getReservaId() {
        return reservaId;
    }

    //SETTERS
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return String.format(
          "huesped{\n\t" +
                  "id: %d,\n\t" +
                  "nombre: %s,\n\t" +
                  "apellido: %s,\n\t" +
                  "fecha_nacimiento: %s,\n\t" +
                  "nacionalidad: %s,\n\t" +
                  "telefono: %s,\n\t" +
                  "reserva_id: %d\n" +
                  "}",
                this.id,
                this.nombre,
                this.apellido,
                this.fechaNacimiento,
                this.nacionalidad,
                this.telefono,
                this.reservaId
        );
    }
}
