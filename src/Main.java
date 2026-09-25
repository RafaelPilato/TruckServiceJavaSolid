import dominio.Caminhao;
import dominio.Mecanico;
import dominio.Motorista;
import dominio.OrdemServico;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Testes ----
        Motorista m = new Motorista("Rafael Pilato", "rafael.pilato@gmail.com");
        Caminhao c1 = new Caminhao("BDT6D88", "AB1234", "Scania", "R450", 2022, m);
        Mecanico m1 = new Mecanico("Adolfo", "00011122233");
        OrdemServico os = new OrdemServico("Troca de oleo", "Realizar troca de oleo mensal", c1);

        System.out.println(os);
        System.out.println();
        if(os.podeSerFinalizada()){
            System.out.println("Sim pode");
        }
        else{
            System.out.println("Não pode");
        }

        os.finalizar(m1, LocalDateTime.parse("2026-09-25T12:13"), "Realizado traca de oleo e filtro");

        if(os.podeSerFinalizada()){
            System.out.println("Sim pode");
        }
        else{
            System.out.println("Não pode");
        }

        System.out.println(os);

        // ------------


    }
}