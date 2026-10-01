package servico;

import java.time.LocalDateTime;

import custeio.CalculadoraCustoServico;
import dominio.Mecanico;
import dominio.OrdemServico;
import notificacao.CanalNotificacao;
import repositorio.OrdemServicoRepositorio;

public class FinalizacaoOrdemServicoService {

    private final OrdemServicoRepositorio repositorio;
    private final CalculadoraCustoServico calculadora;
    private final CanalNotificacao canalNotificacao;

    public FinalizacaoOrdemServicoService(OrdemServicoRepositorio repositorio,
                                          CalculadoraCustoServico calculadora,
                                          CanalNotificacao canalNotificacao) {
        this.repositorio = repositorio;
        this.calculadora = calculadora;
        this.canalNotificacao = canalNotificacao;
    }

    public void finalizar(OrdemServico ordemServico, Mecanico mecanico,
                          LocalDateTime dataHoraFechamento, String descricaoServicoRealizado) {

        // a própria OS valida e lança exceção se algo estiver errado (SRP)
        ordemServico.finalizar(mecanico, dataHoraFechamento, descricaoServicoRealizado);

        double custo = calculadora.calcular(ordemServico);

        repositorio.salvar(ordemServico);

        String destinatario = ordemServico.getCaminhao().getMotorista().getContato();
        String mensagem = "Seu caminhão (placa " + ordemServico.getCaminhao().getPlaca()
                + ") está liberado. OS #" + ordemServico.getId()
                + " finalizada. Custo do serviço: R$ " + custo;

        canalNotificacao.notificar(destinatario, mensagem);
    }
}