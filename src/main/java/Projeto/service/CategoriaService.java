package Projeto.service;

import Projeto.domain.Categoria;
import Projeto.repositories.CategoriaRepository;
import Projeto.service.execptions.ObjectNotFoundException;
import org.omg.PortableServer.POAPackage.ObjectAlreadyActive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repo;
    public Categoria find(Integer id){
          Optional<Categoria> obj = repo.findById(id);
          return obj.orElseThrow(() ->
                  new ObjectNotFoundException("Objeto não encontrado! Id: "+ id + ", Tipo " +
                          Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update (Categoria obj){
         find(obj.getId());
         return repo.save(obj);
    }
}
