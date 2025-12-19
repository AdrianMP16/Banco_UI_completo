import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ConexionBD.getConnection()) {

            System.out.println("✅ Conexión exitosa");

            String sql = "SELECT * FROM usuario";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            System.out.println("DATOS DE LA TABLA USUARIO");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("nombre") + " | " +
                                rs.getDouble("saldo")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ No se pudo conectar: " + e.getMessage());
            e.printStackTrace();
        }

        new Login().setVisible(true);
    }
}

