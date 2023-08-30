package com.controller;

import com.DAO.HuespedDAO;
import com.DAO.ReservaDAO;
import com.factory.ConnectionFactory;
import com.model.Huespedes;

import java.sql.Connection;

public class HuespedController {
    private Connection con;
    private HuespedDAO huespedDAO;
    private Huespedes huesped;


    public HuespedController() {
        this.con = new ConnectionFactory().tryConnection();
        this.huespedDAO = new HuespedDAO(con);
    }

    public void registrar(String nombre, String apellido, String fNacimiento, String nacionalidad, String telefono, Integer resId){
        huesped = new Huespedes(nombre,apellido, fNacimiento, nacionalidad, telefono, resId);
        huespedDAO.registrar(huesped);
    }

    public Integer getLastReservaId(){
        ReservaDAO reservaDAO = new ReservaDAO(new ConnectionFactory().tryConnection());
        return reservaDAO.getLastInsertId();
    }

}
