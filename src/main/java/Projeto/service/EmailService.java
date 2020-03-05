package Projeto.service;

import Projeto.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void SendEmail(SimpleMailMessage msg);
}
