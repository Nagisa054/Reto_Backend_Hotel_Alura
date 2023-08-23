package com.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private String url = "jdbc:mysql://localhost/hotel_Alura_db";
    private String timeZone = "useTimeZone=true";
    private String serverTimeZone = "serverTimeZone=UTC";
    private DataSource dataSource;

    public ConnectionFactory() {
        var pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl(url + "?" + timeZone + "&" + serverTimeZone);
        pooledDataSource.setUser("root");
        pooledDataSource.setPassword("root");
        pooledDataSource.setMaxPoolSize(10);

        this.dataSource = pooledDataSource;

    }

    public Connection tryConnection(){
        try {
            return this.dataSource.getConnection();
        }catch (SQLException e) {
            System.out.println("ERROR DE CONEXION");
            throw  new RuntimeException(e);
        }
    }

}
