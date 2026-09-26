import dominio.Caminhao;
import dominio.Mecanico;
import dominio.Motorista;
import dominio.OrdemServico;
import repositorio.OrdemServicoRepositorioMemoria;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Testes ----
        Motorista m = new Motorista("Rafael Pilato", "rafael.pilato@gmail.com");
        Caminhao c1 = new Caminhao("BDT6D88", "AB1234", "Scania", "R450", 2022, m);
        Mecanico m1 = new Mecanico("Adolfo", "00011122233");
        OrdemServico os = new OrdemServico("Troca de oleo", "Realizar troca de oleo mensal", c1);

        /*
        System.out.println(os);
        System.out.println();
        if(os.podeSerFinalizada()){
            System.out.println("Sim pode");
        }
        else{
            System.out.println("Não pode");
        }
        */

        // Repositorio
        OrdemServicoRepositorioMemoria repositorio = new OrdemServicoRepositorioMemoria();

        repositorio.salvar(os);

        System.out.println(os);

        os.finalizar(m1, LocalDateTime.parse("2026-09-25T12:13"), "Realizado traca de oleo e filtro");

        if(os.podeSerFinalizada()){
            System.out.println("Sim pode");
        }
        else{
            System.out.println("Não pode");
        }

        repositorio.salvar(os);
        System.out.println(os);

        OrdemServico os2 = new OrdemServico("Troca de pneu", "Trocar os pneus da tração", c1);

        repositorio.salvar(os2);

        System.out.println(os2);

        for(OrdemServico oss : repositorio.listarTodas()){
            System.out.println(oss);
        }

        System.out.println(repositorio.buscarPorId(2));

        // ------------


    }
}