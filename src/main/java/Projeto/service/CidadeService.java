package Projeto.service;

import Projeto.domain.Cidade;
import Projeto.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repo;

    public List<Cidade> findAll(Integer estado_id){
        return repo.findCidades(estado_id);
    }

}
