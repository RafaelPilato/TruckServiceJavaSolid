package dominio;

import java.time.LocalDateTime;
import excecao.DadosInvalidosParaFinalizarException;
import excecao.OrdemServicoJaFinalizadaException;

public class OrdemServico {
    private int id;
    private String titulo;
    private String descricaoProblema;
    private LocalDateTime dataHoraAbertura;
    private boolean finalizada;
    private Caminhao caminhao;
    private Mecanico mecanico;
    private LocalDateTime dataHoraFechamento;
    private String descricaoServicoRealizado;

    public OrdemServico(String titulo, String descricaoProblema, Caminhao caminhao) {
        this.titulo = titulo;
        this.descricaoProblema = descricaoProblema;
        this.dataHoraAbertura = LocalDateTime.now();
        this.finalizada = false;
        this.caminhao = caminhao;
    }

    public void definirId(int id){
        this.id = id;
    }

    public boolean podeSerFinalizada(){
        return !finalizada;
    }

    public void finalizar(Mecanico mecanico, LocalDateTime dataHoraFechamento, String descricaoServicoRealizado){
        if(finalizada){
            throw new OrdemServicoJaFinalizadaException("A ordem de serviço já está finalizada.");
        }
        if(mecanico == null){
            throw new DadosInvalidosParaFinalizarException("O mecânico é obrigatório para finalizar.");
        }
        if(dataHoraFechamento == null){
            throw new DadosInvalidosParaFinalizarException("A data de fechamento é obrigatória.");
        }
        if(dataHoraFechamento.isBefore(dataHoraAbertura)){
            throw new DadosInvalidosParaFinalizarException("A data de fechamento não pode ser anterior à abertura.");
        }
        if(descricaoServicoRealizado == null || descricaoServicoRealizado.isBlank()){
            throw new DadosInvalidosParaFinalizarException("A descrição do serviço realizado é obrigatória.");
        }

        this.finalizada = true;
        this.mecanico = mecanico;
        this.dataHoraFechamento = dataHoraFechamento;
        this.descricaoServicoRealizado = descricaoServicoRealizado;
    }

    public int getId() { return id; }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public LocalDateTime getDataHoraAbertura() {
        return dataHoraAbertura;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public LocalDateTime getDataHoraFechamento() {
        return dataHoraFechamento;
    }

    public String getDescricaoServicoRealizado() {
        return descricaoServicoRealizado;
    }

    @Override
    public String toString() {
        return "OrdemServico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricaoProblema='" + descricaoProblema + '\'' +
                ", dataHoraAbertura=" + dataHoraAbertura +
                ", finalizada=" + finalizada +
                ", caminhao=" + caminhao +
                ", mecanico=" + mecanico +
                ", dataHoraFechamento=" + dataHoraFechamento +
                ", descricaoServicoRealizado='" + descricaoServicoRealizado + '\'' +
                '}';
    }
}
