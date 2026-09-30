package custeio;

import dominio.OrdemServico;
import java.time.Duration;

public class CustoPorHoraMecanico implements CalculadoraCustoServico {
    private final double valorHora;

    public CustoPorHoraMecanico(double valorHora) {
        this.valorHora = valorHora;
    }

    @Override
    public double calcular(OrdemServico ordemServico) {
        Duration duracao = Duration.between(
                ordemServico.getDataHoraAbertura(),
                ordemServico.getDataHoraFechamento());
        // Divisão decimal: 90 minutos correspondem a 1,5 hora.
        double horasTrabalhadas = duracao.toSeconds() / 3600.0;
        return valorHora * horasTrabalhadas;
    }
}
