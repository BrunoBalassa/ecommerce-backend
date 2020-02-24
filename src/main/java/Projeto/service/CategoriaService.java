package Projeto.service;

import Projeto.domain.Categoria;
import Projeto.domain.Cliente;
import Projeto.dto.CategoriaDTO;
import Projeto.repositories.CategoriaRepository;
import Projeto.service.execptions.DataIntegrituExcepetion;
import Projeto.service.execptions.ObjectNotFoundException;
import org.omg.PortableServer.POAPackage.ObjectAlreadyActive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Categoria update (Categoria obj){
        Categoria newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrituExcepetion("Não é possivel excluir uma categoria que possui produto");
        }

    }
    public List<Categoria> findAll(){
        return repo.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction),orderBy);
        return repo.findAll(pageRequest);
    }
    public Categoria fromDTO(CategoriaDTO objDTO){
        return new Categoria(objDTO.getId(),objDTO.getName());
    }
    public void updateData(Categoria newOj, Categoria obj){
        newOj.setNome(obj.getNome());
    }
}
