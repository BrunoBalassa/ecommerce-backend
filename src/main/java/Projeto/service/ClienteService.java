package Projeto.service;

import Projeto.domain.Cidade;
import Projeto.domain.Cliente;
import Projeto.domain.Cliente;
import Projeto.domain.Endereco;
import Projeto.domain.enums.TipoCliente;
import Projeto.dto.ClienteDTO;
import Projeto.dto.ClienteNewDTO;
import Projeto.repositories.CidadeRepository;
import Projeto.repositories.ClienteRepository;
import Projeto.repositories.ClienteRepository;
import Projeto.repositories.EnderecoRepository;
import Projeto.service.execptions.DataIntegrituExcepetion;
import Projeto.service.execptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id){
          Optional<Cliente> obj = repo.findById(id);
          return obj.orElseThrow(() ->
                  new ObjectNotFoundException("Objeto não encontrado! Id: "+ id + ", Tipo " +
                          Cliente.class.getName()));
    }
    public Cliente update (Cliente obj){
        Cliente newObj = find(obj.getId());
        updateData(newObj,obj);
        return repo.save(obj);
    }
    public Cliente insert( Cliente obj ){
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;

    }

    public void delete(Integer id){
        find(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrituExcepetion("Não é possivel excluir porque há entidade relacionadas");
        }

    }

    public List<Cliente> findAll(){
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction),orderBy);
        return repo.findAll(pageRequest);
    }
    public Cliente fromDTO(ClienteDTO objDTO){
        return new Cliente(objDTO.getId(),objDTO.getNome(), objDTO.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDTO){
        Cliente cli = new Cliente(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
        Cidade ci = new Cidade(objDTO.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, ci);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDTO.getTelefone1());
        if(objDTO.getTelefone2()!= null){
            cli.getTelefones().add(objDTO.getTelefone2());
        }
        if(objDTO.getTelefone3()!= null){
            cli.getTelefones().add(objDTO.getTelefone3());
        }
        return cli;
    }

    private void updateData(Cliente newObj, Cliente obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
