import com.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnectionFactory {
    public static void main(String[] args)throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        for (int i = 0; i < 15; i++){
            Connection con = connectionFactory.tryConnection();
            System.out.println("Creando conexión numero: " + (i + 1));
        }
    }
}
