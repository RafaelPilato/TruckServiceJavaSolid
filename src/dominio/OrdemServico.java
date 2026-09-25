package dominio;

import java.time.LocalDateTime;

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

    public boolean podeSerFinalizada(){
        return !finalizada;
    }

    public void finalizar(Mecanico mecanico, LocalDateTime dataHoraFechamento, String descricaoServicoRealizado){
        if(finalizada){
            //Já finalizada
        }
        if(mecanico == null || dataHoraFechamento == null){
            //Dados inválidos
        }
        if(dataHoraFechamento.isBefore(dataHoraAbertura)){
            //Dado inválido
        }
        if(descricaoServicoRealizado == null || descricaoServicoRealizado.isBlank()){
            //Dado inválido
        }

        this.finalizada = true;
        this.mecanico = mecanico;
        this.dataHoraFechamento = dataHoraFechamento;
        this.descricaoServicoRealizado = descricaoServicoRealizado;
    }

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
