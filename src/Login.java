import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame{
    private JButton ingresarButton;
    private JTextField InContraseña;
    private JTextField InUsuario;
    private JPanel Panel_img;
    private JPanel Panel_Login;

    public Login(){
        setTitle("PANTALLA PRINCIPAL");
        setSize(550,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(Panel_Login);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user="cliente123";
                String key="clave456";
                String client=InUsuario.getText();
                String contra=InContraseña.getText();
                if (user.equals(client) && key.equals(contra)){
                    JOptionPane.showMessageDialog(null,"Credenciales correctas");
                    dispose();
                    new BancoForm(user).setVisible(true);
                } else if (client.trim().isEmpty() || contra.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Credenciales vacias");
                }else {
                    JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos");
                    InContraseña.setText("");
                    InUsuario.setText("");
                }
            }
        });
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
