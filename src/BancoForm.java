import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BancoForm extends JFrame{
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
    private double saldoActual=10000.00;
    private StringBuilder historial = new StringBuilder();

    private void agregarHistorial(String texto) {
        historial.append(texto).append("\n");
        historialArea.setText(historial.toString());
    }

    private void mostrarPanel(String nombrePanel) {
        CardLayout cl = (CardLayout) Inicial.getLayout();
        cl.show(Inicial, nombrePanel);
    }

    private void actualizarSaldo() {
        saldo.setText("$ " + saldoActual);
    }


    public BancoForm(String user){
        setTitle("Banco form");
        setSize(1000,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);
        setContentPane(Panel_Banco);

        this.user=user;

        usuario.setText(user);
        saldo.setText("$ "+saldoActual);

        Inicial.setLayout(new CardLayout());
        Inicial.add(Depositar,"depositar");
        Inicial.add(Retirar,"retirar");
        Inicial.add(Transferir,"transferir");


        depositarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("depositar");
            }
        });
        retirarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("retirar");
            }
        });
        transferirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("transferir");
            }
        });
        depositarButton.addActionListener(e -> {
            try {
                double monto = Double.parseDouble(cantidadDepositar.getText());

                if (monto <= 0) {
                    JOptionPane.showMessageDialog(this, "Ingrese un monto válido");
                    return;
                }

                saldoActual += monto;
                agregarHistorial("Depósito de $" + monto);
                actualizarSaldo();
                cantidadDepositar.setText("");
                JOptionPane.showMessageDialog(this, "Depósito exitoso");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido");
            }
        });
        retirarButton1.addActionListener(e -> {
            try {
                double monto = Double.parseDouble(cantidadRetirar.getText());

                if (monto <= 0) {
                    JOptionPane.showMessageDialog(this, "Ingrese un monto válido");
                    return;
                }

                if (monto > saldoActual) {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente");
                    return;
                }

                saldoActual -= monto;
                agregarHistorial("Retiro de $" + monto);
                actualizarSaldo();
                cantidadRetirar.setText("");
                JOptionPane.showMessageDialog(this, "Retiro realizado");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido");
            }
        });
        tranferirButton.addActionListener(e -> {
            try {

                String cuenta = destinatario.getText().trim();
                double monto = Double.parseDouble(cantidadTranferir.getText());

                if (cuenta.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese una cuenta destino");
                    return;
                }

                if (monto <= 0) {
                    JOptionPane.showMessageDialog(this, "Ingrese un monto válido");
                    return;
                }

                if (monto > saldoActual) {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente");
                    return;
                }

                saldoActual -= monto;
                agregarHistorial("Transferencia de $" + monto + " a " + cuenta);
                actualizarSaldo();

                destinatario.setText("");
                cantidadTranferir.setText("");

                JOptionPane.showMessageDialog(this,
                        "Transferencia realizada a " + cuenta + " por $ "+monto);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido");
            }
        });
        salirButton.addActionListener(e -> {
            JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(salirButton);
            ventanaActual.dispose();

            Login login = new Login();
            login.setVisible(true);
        });

    }
}
