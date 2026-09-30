package custeio;

import dominio.OrdemServico;

public interface CalculadoraCustoServico {
    // Recebe uma OS finalizada, com datas de abertura e fechamento válidas.
    double calcular(OrdemServico ordemServico);
}
