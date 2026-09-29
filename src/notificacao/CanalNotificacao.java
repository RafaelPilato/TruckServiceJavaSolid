package notificacao;

public interface CanalNotificacao {
    void notificar(String destinatario, String mensagem);
}