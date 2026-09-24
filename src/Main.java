import dominio.Caminhao;
import dominio.Motorista;

public class Main {
    public static void main(String[] args) {
        Motorista m = new Motorista();
        Caminhao c1 = new Caminhao("BDT6D88", "AB1234", "Scania", "R450", 2022, m);
    }
}