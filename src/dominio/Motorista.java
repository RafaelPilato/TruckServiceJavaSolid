package dominio;

public class Motorista {
    private int id;
    private String nome;
    private String contato;

    public Motorista(String nome, String contato) {
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O nome é obrigatório!");
        }
        if(contato == null || nome.isBlank()){
            throw new IllegalArgumentException("O contato é obrigatório!");
        }
        this.nome = nome;
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public String getContato() {
        return contato;
    }
}
