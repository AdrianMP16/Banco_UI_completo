import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://ukwsbe9qsn9b1cvo:gRuDpez80UqNtGXFFdZj@bjhreqgoaghtpvvmphmi-mysql.services.clever-cloud.com:3306/bjhreqgoaghtpvvmphmi";
    private static final String USER = "ukwsbe9qsn9b1cvo";
    private static final String PASSWORD = "gRuDpez80UqNtGXFFdZj";

    private static Connection connection;


    private ConexionBD() {}


    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}