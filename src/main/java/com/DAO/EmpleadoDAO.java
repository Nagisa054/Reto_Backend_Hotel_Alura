package com.DAO;

import com.views.MenuUsuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoDAO {
    final private Connection con;

    public EmpleadoDAO(Connection connection) {
        this.con = connection;

    }

    public Boolean Login(String usuario, String clave) {
        try {
            var query = "SELECT nombre_en_sistema, clave_en_sistema FROM Empleado"
                        +" WHERE nombre_en_sistema = '"+usuario+"'"
                        +" AND clave_en_sistema = '"+clave+"'";
            final PreparedStatement statement = con.prepareStatement(query);
            System.out.println(query);
            try (statement) {
                final ResultSet resultSet = statement.executeQuery();
                System.out.println();

                while (resultSet.next()) {
                    String nombreDB = resultSet.getString("nombre_en_sistema");
                    String claveDB = resultSet.getString("clave_en_sistema");

                    if (usuario.equals(nombreDB) && clave.equals(claveDB)) {
                        MenuUsuario menu = new MenuUsuario();
                        menu.setVisible(true);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
        return false;
    }
}




