import java.sql.*;

public class Main{
    public static void main(String[] args) {
        String url = "jdbc:mysql://ukwsbe9qsn9b1cvo:gRuDpez80UqNtGXFFdZj@bjhreqgoaghtpvvmphmi-mysql.services.clever-cloud.com:3306/bjhreqgoaghtpvvmphmi";
        String user = "ukwsbe9qsn9b1cvo";
        String password = "gRuDpez80UqNtGXFFdZj";

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
