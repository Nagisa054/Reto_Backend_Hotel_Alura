package com.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Esta clase reprecenta la tabla Reserva de la base de datos.
 *
 * @since 20/08/2023
 * @author Juan Pablo Rojas Cardozo | Nagisa054
 */
public class Reserva {
    Integer id;
    String fechaEntrada;
    String fechaSalida;
    BigDecimal precio;
    String formaPago;

    /**
     * @param fechaEntrada
     * @param fechaSalida
     * @param precio
     * @param formaPago
     */
    public Reserva(String fechaEntrada, String fechaSalida, BigDecimal precio, String formaPago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precio = precio;
        this.formaPago = formaPago;
    }

    public Reserva(int id, String fechaEntrada, String fechaSalida, Float precio,String formaPago) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precio = BigDecimal.valueOf(precio);
        this.formaPago = formaPago;
    }

    //GETERS
    public Integer getId() {
        return id;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public String getFormaPago() {
        return formaPago;
    }

    //SETERS
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    @Override
    public String toString(){
        Integer valor = Integer.valueOf(String.valueOf(this.precio));
        return String.format(
                "Reserva{\n\t" +
                        "id: %d,\n\t" +
                        "fecha_entrada: %s,\n\t" +
                        "fecha_salida: %s,\n\t" +
                        "valor: %d,\n\t" +
                        "forma Pago: %s\n\t"
                        + "}",
                this.id,
                this.fechaEntrada,
                this.fechaSalida,
                valor,
                this.formaPago
        );
    }
}

