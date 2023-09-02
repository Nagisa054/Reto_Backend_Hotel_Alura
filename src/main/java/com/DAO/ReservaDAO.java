package com.DAO;

import com.google.protobuf.DescriptorProtos;
import com.model.Reserva;
import com.views.RegistroHuesped;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Aquí se maneja toda la lógica relacionada con la tabla "Reserva" de la base de datos.
 * @since 28/08/2023
 * @author Juan Pablo Rojas | Nagisa054
 */
public class ReservaDAO {
    final private Connection con;

    /**
     * @param connection
     */
    public ReservaDAO(Connection connection){
        this.con = connection;

    }

    /**
     *
     * @return un Integer, el ID de la última reserva guardada en la base de datos.
     */
     public Integer getLastInsertId(){
         /*
         * Este método es usado por la clase HuespedController para
         * relacionar el registro del huesped y sú reserva.0
         */
         try {

         int resultado = 0;
         var query = "SELECT LAST_INSERT_ID(id) FROM reserva";

         PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         try(statement) {

             final ResultSet resultSet = statement.executeQuery();
             try(resultSet) {
                 while (resultSet.next()) resultado = resultSet.getInt(1);
             }
         }
    //         con.close();
             return resultado;
         }catch (SQLException e) {
             throw new RuntimeException(e);
         }
    }

    /**
     * Aquí se crean y guardan los registros de las reservas.
     * @param reserva
     */
    public void guardar(Reserva reserva){
        // consulta SQL sin los valores
        try{
            var query = "INSERT INTO reserva" +
                    "(fecha_entrada, fecha_salida, valor, forma_pago)" +
                    "VALUES(?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            try(statement) {
                //Asignación de valores a la consulta
                statement.setString(1, reserva.getFechaEntrada());
                statement.setString(2, reserva.getFechaSalida());
                statement.setBigDecimal(3, reserva.getPrecio());
                statement.setString(4, reserva.getFormaPago());
                //Sé ejecuta la consulta
                statement.execute();

                // Se crea un objeto de tipo ResultSet
                final ResultSet resultSet = statement.getGeneratedKeys();
                try(resultSet) {

                    // A continuación se recorre el resultSet
                    while (resultSet.next()){
                        //sé asigna el id a "reserva". Este id es el valor de la primera columna del resultSet
                        reserva.setId(resultSet.getInt(1));

                        System.out.println(reserva);
                    }
                }
                // Se crea una ventana de Registro huéspedes y se muestra.
                RegistroHuesped registro = new RegistroHuesped();
                registro.setVisible(true);
            }
            con.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @return un List de Reserva
     */
    public List<Reserva> listar(){
        /*
         * Sé crea un List de tipo Reserva, este almacenará todos los registros de
         * la tabla "reserva" de ña base de datos.
         */
        List<Reserva> resultado = new ArrayList<Reserva>();

        // consulta SQL
        String query = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reserva";

         /*
         * Se crea el statement y el resultSet en un try whit resources.
         * Con el statement ejecutamos la consulta SQL en la base de datos
         * y el resultSet es usado para guardar los registros en el List de
         * reservas.
         */
        try (PreparedStatement statement = con.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                //creamos una Reserva con los datos del resultSet.
                Reserva fila = new Reserva(resultSet.getInt("id"),
                        resultSet.getString("fecha_entrada"),
                        resultSet.getString("fecha_salida"),
                        resultSet.getBigDecimal("valor"),
                        resultSet.getString("forma_pago"));
                // adicionamos el objeto a la lista.
                resultado.add(fila);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // sé retorna la lista.
        return resultado;
    }

    /**
     *
     * @param id
     * @param fechaEntrada
     * @param fechaSalida
     * @param precio
     * @param formaPago
     * @return un Integer con la cantidad de registros modificados en la base de datos.
     */
    public Integer editar(int id, String fechaEntrada, String fechaSalida, BigDecimal precio, String formaPago){
        // Consulta SQL.
        String query = "UPDATE reserva SET " +
                "id = ?, fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_pago = ? WHERE id = ?";
        /*
         * Sé crea un PreparedStatement con la consulta SQl dentro de
         * un try whit resources para contener un SQLException.
         */
        try(PreparedStatement statement = con.prepareStatement(query)) {
            // se prepara la consulta SQL con los parametros del método.
            statement.setInt(1, id);
            statement.setString(2, fechaEntrada);
            statement.setString(3, fechaSalida);
            statement.setBigDecimal(4, precio);
            statement.setString(5, formaPago);
            statement.setInt(6, id);

            // Ejecución de la consulta.
            statement.execute();

            // mensaje de que el registro se editó exitosamente.
            JOptionPane.showMessageDialog(null, "Registro Modificado Exitosamente.");

            //Se retorna la cantidad de filas Modificadas en la base de datos.
            return statement.getUpdateCount();
        }catch (SQLException e){
                throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param id
     * @return un Integer con la cantidad de filas modificadas en la base de datos.
     */
    public Integer eliminar(int id){
        // consulta SQL.
        String query = "DELETE FROM reserva WHERE id = ?;";

        /*
         * Sé crea un PreparedStatement con la consulta SQl dentro de
         * un try whit resources para contener un SQLException.
         */
        try (PreparedStatement statement = con.prepareStatement(query)) {

            //se prepara la consulta SQL con los parametros del método.
            statement.setInt(1, id);

            //Se desactiva la restricción de clave Foránea en la base de datos.
            desactivarClaveForanea();
            //Se ejecuta la consulta.
            statement.execute();
            //Se reactiva la restricción de clave Foránea en la base de datos.
            activarClaveForanea();

            //Se retorna la cantidad de filas Modificadas en la base de datos.
            return statement.getUpdateCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Desactiva la Restricción de clave Foránea en la base de datos.
     */
    private void desactivarClaveForanea(){
        try (PreparedStatement statement = con.prepareStatement("SET FOREIGN_KEY_CHECKS = 0")) {
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Activa la Restricción de clave Foránea en la base de datos.
     */
    private void activarClaveForanea(){
        try (PreparedStatement statement = con.prepareStatement("SET FOREIGN_KEY_CHECKS = 1")) {
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
