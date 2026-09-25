package repositorio;

import dominio.OrdemServico;

import java.util.List;
import java.util.Optional;

public class OrdemServicoRepositorioMemoria implements OrdemServicoRepositorio{
    @Override
    public void salvar(OrdemServico os) {

    }

    @Override
    public Optional<OrdemServico> buscarPorId(int id) {
        return Optional.empty();
    }

    @Override
    public List<OrdemServico> listarTodas() {
        return List.of();
    }
}
