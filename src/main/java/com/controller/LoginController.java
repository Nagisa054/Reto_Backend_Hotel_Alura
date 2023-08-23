package com.controller;

import com.DAO.EmpleadoDAO;
import com.factory.ConnectionFactory;

import java.sql.Connection;

public class LoginController {
    private EmpleadoDAO empleadoDAO;
    private Connection con;

    public LoginController(){
        con = new ConnectionFactory().tryConnection();
        this.empleadoDAO = new EmpleadoDAO(con);
    }
    public Boolean login(String nombre, String clave) {
        return empleadoDAO.Login(nombre, clave);
    }


}
