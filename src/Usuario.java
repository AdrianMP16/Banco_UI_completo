public class Usuario {
    private String nombre;
    private double saldo;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.saldo = 1000.00;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    public boolean retirar(double monto) {
        if (monto > saldo) return false;
        saldo -= monto;
        return true;
    }
}
