import com.model.Empleado;

public class TestEmpleado {
    public static void main(String[] args) {
//        Integer id, String nombre, String apellido, String nombre_sis, String clave_sis
        Empleado empleado = new Empleado(1, "Juan", "apellido", "root", "123");
        System.out.println(empleado);
    }
}
