import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class BancoForm extends JFrame {

    private JButton salirButton;
    private JButton depositarButton1;
    private JButton retirarButton;
    private JButton transferirButton;
    private JPanel Inicial;
    private JPanel Transferir;
    private JPanel Retirar;
    private JPanel Depositar;
    private JButton retirarButton1;
    private JButton tranferirButton;
    private JTextField destinatario;
    private JTextField cantidadTranferir;
    private JTextField cantidadRetirar;
    private JButton depositarButton;
    private JTextField cantidadDepositar;
    private JLabel saldo;
    private JLabel usuario;
    private JPanel Panel_Banco;
    private JTextArea historialArea;

    private String user;
    private double saldoActual;

    private StringBuilder historial = new StringBuilder();

    public BancoForm(String user) {

        this.user = user;

        setTitle("Banco");
        setSize(1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(Panel_Banco);

        usuario.setText(user);

        Inicial.setLayout(new CardLayout());
        Inicial.add(Depositar, "depositar");
        Inicial.add(Retirar, "retirar");
        Inicial.add(Transferir, "transferir");

        cargarSaldo();

        depositarButton1.addActionListener(e -> mostrarPanel("depositar"));
        retirarButton.addActionListener(e -> mostrarPanel("retirar"));
        transferirButton.addActionListener(e -> mostrarPanel("transferir"));

        depositarButton.addActionListener(e -> depositar());
        retirarButton1.addActionListener(e -> retirar());
        tranferirButton.addActionListener(e -> transferir());

        salirButton.addActionListener(e -> {
            dispose();
            new Login().setVisible(true);
        });

        setVisible(true);
    }

    // ---------------- MÉTODOS ----------------

    private void mostrarPanel(String nombre) {
        CardLayout cl = (CardLayout) Inicial.getLayout();
        cl.show(Inicial, nombre);
    }

    private void agregarHistorial(String texto) {
        historial.append(texto).append("\n");
        historialArea.setText(historial.toString());
    }

    private void cargarSaldo() {
        String sql = "SELECT saldo FROM usuario WHERE nombre = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                saldoActual = rs.getDouble("saldo");
                saldo.setText("$ " + saldoActual);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar saldo");
            e.printStackTrace();
        }
    }

    private void depositar() {
        try {
            double monto = Double.parseDouble(cantidadDepositar.getText());
            if (monto <= 0) throw new Exception();

            double nuevoSaldo = saldoActual + monto;
            saldoActual += monto;
            saldo.setText("$ "+saldoActual);
            actualizarSaldo();

            agregarHistorial("Depósito: $" + monto);
            cantidadDepositar.setText("");
            JOptionPane.showMessageDialog(this, "Depósito exitoso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Monto inválido");
        }
    }

    private void retirar() {
        try {
            double monto = Double.parseDouble(cantidadRetirar.getText());
            if (monto <= 0 || monto > saldoActual) throw new Exception();

            double nuevoSaldo = saldoActual - monto;
            saldoActual -= monto;
            saldo.setText("$ "+saldoActual);
            actualizarSaldo();

            agregarHistorial("Retiro: $" + monto);
            cantidadRetirar.setText("");
            JOptionPane.showMessageDialog(this, "Retiro exitoso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Monto inválido o saldo insuficiente");
        }
    }

    private void transferir() {
        try {
            String destino = destinatario.getText().trim();
            double monto = Double.parseDouble(cantidadTranferir.getText());

            if (destino.isEmpty() || monto <= 0 || monto > saldoActual)
                throw new Exception();

            double nuevoSaldo = saldoActual - monto;
            saldoActual -= monto;
            saldo.setText("$ "+saldoActual);
            actualizarSaldo();

            agregarHistorial("Transferencia a " + destino + ": $" + monto);

            destinatario.setText("");
            cantidadTranferir.setText("");
            JOptionPane.showMessageDialog(this, "Transferencia exitosa");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }
    private void actualizarSaldo() {

        String sql = "UPDATE usuario SET saldo = ? WHERE nombre = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, saldoActual);
            ps.setString(2, user);

            int filas = ps.executeUpdate();
            System.out.println("Filas actualizadas: " + filas);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar saldo en BD");
            e.printStackTrace();
        }
    }

}
