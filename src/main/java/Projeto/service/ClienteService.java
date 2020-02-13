package Projeto.service;

import Projeto.domain.Categoria;
import Projeto.domain.Cliente;
import Projeto.repositories.CategoriaRepository;
import Projeto.repositories.ClienteRepository;
import Projeto.service.execptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;
    public Cliente buscar(Integer id){
          Optional<Cliente> obj = repo.findById(id);
          return obj.orElseThrow(() ->
                  new ObjectNotFoundException("Objeto não encontrado! Id: "+ id + ", Tipo " +
                          Cliente.class.getName()));
    }
}
