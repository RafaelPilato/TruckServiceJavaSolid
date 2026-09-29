package notificacao;

public class NotificacaoWhatsApp implements CanalNotificacao {

    @Override
    public void notificar(String destinatario, String mensagem) {
        if (destinatario == null || destinatario.isBlank()) {
            System.out.println("[WHATSAPP] Falha no envio: destinatário não informado.");
            return;
        }
        System.out.println("[WHATSAPP para " + destinatario + "] " + mensagem);
    }
}