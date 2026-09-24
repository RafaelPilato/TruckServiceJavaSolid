package dominio;

public class Mecanico {
    private int id;
    private String nome;
    private String cpf;

    public Mecanico(String nome, String cpf) {
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O nome é obrigatório!");
        }
        if(cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("O CPF é obrigatório!");
        }

        this.nome = nome;
        this.cpf = cpf;
    }
}
