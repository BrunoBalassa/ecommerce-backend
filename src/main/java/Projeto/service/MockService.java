package Projeto.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockService  extends AbstratcEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockService.class);


    @Override
    public void SendEmail(SimpleMailMessage msg) {
        LOG.info("Simulando envio de Email...");
        LOG.info(msg.toString());
        LOG.info("Email enviado");

    }
}
