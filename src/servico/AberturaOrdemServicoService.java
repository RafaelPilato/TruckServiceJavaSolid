package servico;

import dominio.Caminhao;
import dominio.OrdemServico;
import repositorio.OrdemServicoRepositorio;

public class AberturaOrdemServicoService {

    private final OrdemServicoRepositorio repositorio;

    public AberturaOrdemServicoService(OrdemServicoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public OrdemServico abrir(Caminhao caminhao, String titulo, String descricaoProblema) {
        OrdemServico ordemServico = new OrdemServico(titulo, descricaoProblema, caminhao);
        repositorio.salvar(ordemServico);
        return ordemServico;
    }
}