import dominio.Caminhao;
import dominio.Mecanico;
import dominio.Motorista;

public class Main {
    public static void main(String[] args) {
        Motorista m = new Motorista("Rafael Pilato", "rafael.pilato@gmail.com");
        Caminhao c1 = new Caminhao("BDT6D88", "AB1234", "Scania", "R450", 2022, m);
        Mecanico m1 = new Mecanico("Adolfo", "00011122233");
    }
}