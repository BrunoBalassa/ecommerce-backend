package Projeto.service;

import Projeto.domain.Cliente;
import Projeto.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstratcEmailService  implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Override
    public  void sendOrderConfirmationEmail(Pedido obj){
        SimpleMailMessage sm = prepareSimpleMailMAssageFromPedido(obj);
        SendEmail(sm);
    }

    protected  SimpleMailMessage prepareSimpleMailMAssageFromPedido(Pedido obj){
            SimpleMailMessage sm = new SimpleMailMessage();
            sm.setTo(obj.getCliente().getEmail());
            sm.setFrom(sender);
            sm.setSubject("Pedido Confirmado! Codigo: " + obj.getId());
            sm.setSentDate(new Date(System.currentTimeMillis()));
            sm.setText(obj.toString());
            return sm;
    }
    @Override
    public void sendNewPasswordEmail(Cliente cliente, String newPass){
        SimpleMailMessage sm = prepareNewPasswordEmail(cliente, newPass);
        SendEmail(sm);
    }

    protected  SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(cliente.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha" + newPass);
        return sm;
    }
}
