import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {

    private JButton ingresarButton;
    private JTextField InContraseña;
    private JTextField InUsuario;
    private JPanel Panel_img;
    private JPanel Panel_Login;

    private final String URL = "jdbc:mysql://localhost:3306/banco";
    private final String USER = "root";
    private final String PASSWORD = "root";

    public Login() {
        setTitle("PANTALLA PRINCIPAL");
        setSize(550, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(Panel_Login);
        setLocationRelativeTo(null);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String usuario = InUsuario.getText().trim();
                String contrasena = InContraseña.getText().trim();

                if (usuario.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Campos vacíos");
                    return;
                }

                if (validarLogin(usuario, contrasena)) {
                    JOptionPane.showMessageDialog(null, "Credenciales correctas");
                    dispose();
                    new BancoForm(usuario).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                    InUsuario.setText("");
                    InContraseña.setText("");
                }
            }
        });
    }

    private boolean validarLogin(String usuario, String contrasena) {

        String sql = "SELECT * FROM usuario WHERE nombre = ? AND contrasena = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión");
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void createUIComponents() {
        Panel_img = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = new ImageIcon("src/imagenes/logo.png").getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
    }
}

