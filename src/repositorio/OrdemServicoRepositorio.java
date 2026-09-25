package repositorio;

import dominio.OrdemServico;

import java.util.List;
import java.util.Optional;

public interface OrdemServicoRepositorio {
    void salvar(OrdemServico os);
    Optional<OrdemServico> buscarPorId(int id);
    List<OrdemServico> listarTodas();
}
