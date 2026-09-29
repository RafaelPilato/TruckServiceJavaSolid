package notificacao;

public class NotificacaoEmail implements CanalNotificacao {

    @Override
    public void notificar(String destinatario, String mensagem) {
        if (destinatario == null || destinatario.isBlank()) {
            System.out.println("[E-MAIL] Falha no envio: destinatário não informado.");
            return;
        }
        System.out.println("[E-MAIL para " + destinatario + "] " + mensagem);
    }
}