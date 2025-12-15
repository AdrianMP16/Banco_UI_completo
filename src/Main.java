import java.sql.*;

public class Main{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/banco";
        String user = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql="select * from usuario";
            Statement stm = connection.createStatement();
            ResultSet rs=stm.executeQuery(sql);

            System.out.println("DATOS DE LA TABLA");
            if (connection != null) {
                System.out.println("Conexion exitosa");
            }

        } catch (SQLException e) {
            System.out.println("No se pudo conectar: " + e.getMessage());
        }

        new Login().setVisible(true);
    }
}
