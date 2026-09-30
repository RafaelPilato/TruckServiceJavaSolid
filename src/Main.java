import dominio.Caminhao;
import dominio.Mecanico;
import dominio.Motorista;
import dominio.OrdemServico;
import repositorio.*;

import custeio.CalculadoraCustoServico;
import custeio.CustoPorHoraMecanico;
import custeio.CustoGarantia;
import excecao.OrdemServicoException;

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

        // Simula um serviço de três horas, sem depender da data do computador.
        os.finalizar(m1, os.getDataHoraAbertura().plusHours(3), "Realizada troca de óleo e filtro");

        CalculadoraCustoServico calculadora = new CustoPorHoraMecanico(50.0);
        System.out.println("Custo por hora (esperado 150.0): " + calculadora.calcular(os));

        // A mesma interface permite trocar a regra de cálculo.
        calculadora = new CustoGarantia();
        System.out.println("Custo em garantia (esperado 0.0): " + calculadora.calcular(os));

        try {
            os.finalizar(m1, os.getDataHoraAbertura().plusHours(3), "Nova tentativa");
        } catch (OrdemServicoException e) {
            System.out.println("Falha ao finalizar: " + e.getMessage());
        }

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

        try {
            os2.finalizar(null, os2.getDataHoraAbertura().plusHours(3), "Troca dos pneus");
        } catch (OrdemServicoException e) {
            System.out.println("Falha ao finalizar: " + e.getMessage());
        }

        System.out.println(os2);

        for(OrdemServico oss : repositorio.listarTodas()){
            System.out.println(oss);
        }

        System.out.println(repositorio.buscarPorId(2));

        // ------------


    }
}
