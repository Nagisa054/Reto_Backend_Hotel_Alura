import com.controller.LoginController;
import com.factory.ConnectionFactory;

import java.sql.Connection;

public class TestLoginController {
    public static void main(String[] args) {
        Connection con = new ConnectionFactory().tryConnection();
        boolean valido;

        String aa = "admin";//nombre correcto
        String ba = "Admin";//clave falsa

        String ab = "admin";//nombre correcto
        String bb = "admin";//clave correcta

        String ac = "Juan";//nombre falso
        String bc = "admin1";//clave falsa

        valido = new LoginController().login(aa, ba);
        System.out.println(String.format(
                "El inreso con usuario: %s y contraseña: %s es: "
                        + valido + ".", aa, ba
        ));

        valido = new LoginController().login(ab, bb);
        System.out.println(String.format(
                "El ingreso con usuario: %s y contraseña: %s es: "
                        + valido + ".", ab, bb
        ));

        valido = new LoginController().login(ac, bc);
        System.out.println(String.format(
                "El ingreso con usuario: %s y contraseña: %s es "
                        + valido + ".", ac, bc
        ));

    }

}
