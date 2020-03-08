package Projeto.service;

import Projeto.domain.Cliente;
import Projeto.repositories.ClienteRepository;
import Projeto.service.execptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    private Random rand = new Random();

    @Autowired
    private EmailService emailService;
    public void sendNewPassWord(String email){
        Cliente cliente = clienteRepository.findByEmail(email);
        if(email == null){
            throw  new ObjectNotFoundException("Email não encontrado");
        }
        String newPass = newPassword();
        cliente.setSenha(pe.encode(newPass));
        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPass);
    }

    private String newPassword() {
        char[]vet = new char[10];
        for(int i = 0; i< 10; i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if(opt == 0){
            return (char)(rand.nextInt(10) + 48);
        }else if (opt == 1 ){
            return (char)(rand.nextInt(26)+65);
        }
        else {
            return (char)(rand.nextInt(26)+ 97);

        }
    }
}
