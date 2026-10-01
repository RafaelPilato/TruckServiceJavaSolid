import dominio.Caminhao;
import dominio.Mecanico;
import dominio.Motorista;
import dominio.OrdemServico;
import repositorio.OrdemServicoRepositorio;
import repositorio.OrdemServicoRepositorioMemoria;

import custeio.CustoPorHoraMecanico;
import custeio.CustoGarantia;
import notificacao.NotificacaoEmail;
import notificacao.NotificacaoWhatsApp;
import excecao.OrdemServicoException;

import servico.AberturaOrdemServicoService;
import servico.FinalizacaoOrdemServicoService;

public class Main {
    public static void main(String[] args) {

        OrdemServicoRepositorio repositorio = new OrdemServicoRepositorioMemoria();
        AberturaOrdemServicoService aberturaService = new AberturaOrdemServicoService(repositorio);

        // ---------- Cenário 1: cobrança por hora + notificação por e-mail ----------
        Motorista motorista1 = new Motorista("Rafael Pilato", "rafael.pilato@gmail.com");
        Caminhao caminhao1 = new Caminhao("BDT6D88", "AB1234", "Scania", "R450", 2022, motorista1);
        Mecanico mecanico1 = new Mecanico("Geronimo", "00011122233");

        OrdemServico os1 = aberturaService.abrir(caminhao1, "Troca de óleo", "Realizar troca de óleo mensal");

        FinalizacaoOrdemServicoService finalizacaoCenario1 = new FinalizacaoOrdemServicoService(
                repositorio,
                new CustoPorHoraMecanico(50.0),
                new NotificacaoEmail()
        );

        finalizacaoCenario1.finalizar(os1, mecanico1, os1.getDataHoraAbertura().plusHours(3), "Realizada troca de óleo e filtro");
        System.out.println(os1);

        // ---------- Cenário 2: serviço em garantia + notificação por WhatsApp ----------
        Motorista motorista2 = new Motorista("Douglas Lima", "5541999998888");
        Caminhao caminhao2 = new Caminhao("XYZ1A23", "CD5678", "Volvo", "FH540", 2023, motorista2);
        Mecanico mecanico2 = new Mecanico("Jonas Pereira", "11122233344");

        OrdemServico os2 = aberturaService.abrir(caminhao2, "Troca de pneu", "Trocar os pneus da tração");

        FinalizacaoOrdemServicoService finalizacaoCenario2 = new FinalizacaoOrdemServicoService(
                repositorio,
                new CustoGarantia(),
                new NotificacaoWhatsApp()
        );

        finalizacaoCenario2.finalizar(os2, mecanico2, os2.getDataHoraAbertura().plusHours(2), "Troca dos pneus da tração");
        System.out.println(os2);

        // ---------- Teste de extensão: tentar finalizar de novo (deve falhar) ----------
        try {
            finalizacaoCenario1.finalizar(os1, mecanico1, os1.getDataHoraAbertura().plusHours(4), "Nova tentativa");
        } catch (OrdemServicoException e) {
            System.out.println("Falha ao finalizar: " + e.getMessage());
        }

        System.out.println("Todas as OS cadastradas:");
        for (OrdemServico os : repositorio.listarTodas()) {
            System.out.println(os);
        }
    }
}