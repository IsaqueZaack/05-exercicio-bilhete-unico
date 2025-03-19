import java.util.Random;

public class Bilhete {
    static final double tarifaBase = 5.20;
    double saldo;
    long numero;
    Usuario usuario;

    public Bilhete(Usuario usuario) {
        this.numero = gerarNumero();
        this.usuario = usuario;
    }

    private long gerarNumero() {
        Random rd = new Random();
        return rd.nextLong(1000, 10000);
    }

    // método para carregar o bilhete
    public void carregar(double valor) {
        saldo += valor;
    }

    // método para consultar o saldo
    public double consultarSaldo() {
        return saldo;
    }

    // método para passar na catraca
    public String passarNaCatraca() {
        double debito = tarifaBase / 2;
        if(usuario.perfil.equalsIgnoreCase("comum")) {
            debito = tarifaBase;
        }
        if(saldo >= debito) {
            saldo -= debito;
            return "Catraca liberada";
        }
        return "Saldo insuficiente";
    }
}
