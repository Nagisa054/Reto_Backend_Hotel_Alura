package com.DAO;

import com.model.Reserva;
import com.util.TransformarFecha;
import com.views.RegistroHuesped;

import java.sql.*;

public class ReservaDAO {
    final private Connection con;

    public ReservaDAO(Connection connection){
        this.con = connection;

    }

    public void guardar(Reserva reserva){
        try{
            var query = "INSERT INTO reserva" +
                    "(fecha_entrada, fecha_salida, valor, forma_pago)" +
                    "VALUES(?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            try(statement) {
                statement.setString(1, reserva.getFechaEntrada());
                statement.setString(2, reserva.getFechaSalida());
                statement.setBigDecimal(3, reserva.getPrecio());
                statement.setString(4, reserva.getFormaPago());
                System.out.println(query + "\n"
                                + reserva.getFechaEntrada() + "\n"
                                + reserva.getFechaSalida() + "\n"
                                + reserva.getPrecio()  + "\n"
                                + reserva.getFormaPago()  + "\n");
                statement.execute();//Sé ejecuta la query

                final ResultSet resultSet = statement.getGeneratedKeys();
                try(resultSet) {
                    while (resultSet.next()){
                        reserva.setId(resultSet.getInt(1));

                        System.out.println("Se a creado la reserva: " + reserva );
                    }
                }
                RegistroHuesped registro = new RegistroHuesped();
                registro.setVisible(true);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
