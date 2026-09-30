package excecao;

public abstract class OrdemServicoException extends RuntimeException {
    public OrdemServicoException(String mensagem) {
        super(mensagem);
    }
}
