package repositorio;

import dominio.OrdemServico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdemServicoRepositorioMemoria implements OrdemServicoRepositorio{
    private ArrayList<OrdemServico> ordensServico = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public void salvar(OrdemServico os) {
        if(os.getId() == 0){
            os.definirId(proximoId);
            proximoId++;
            ordensServico.add(os);
        }
        else{
            for(int i=0; i < ordensServico.size(); i++){
                if(os.getId() == ordensServico.get(i).getId()){
                    ordensServico.set(i, os);
                    return;
                }
            }
        }
    }

    @Override
    public Optional<OrdemServico> buscarPorId(int id) {
        for (OrdemServico os : ordensServico){
            if(os.getId() == id){
                return Optional.of(os);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<OrdemServico> listarTodas() {
        return new ArrayList<>(ordensServico);
    }
}
