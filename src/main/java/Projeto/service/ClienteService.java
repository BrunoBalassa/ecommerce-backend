package Projeto.service;

import Projeto.domain.Cidade;
import Projeto.domain.Cliente;
import Projeto.domain.Endereco;
import Projeto.domain.enums.Perfil;
import Projeto.domain.enums.TipoCliente;
import Projeto.dto.ClienteDTO;
import Projeto.dto.ClienteNewDTO;
import Projeto.repositories.ClienteRepository;
import Projeto.repositories.EnderecoRepository;
import Projeto.security.UserSS;
import Projeto.service.execptions.AuthorizationExeception;
import Projeto.service.execptions.DataIntegrituExcepetion;
import Projeto.service.execptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("{$/img.prefix.client.profile}")
    private String prefix;

    public Cliente find(Integer id){
        UserSS user = UserService.authenticated();
        if(user == null || ! user.hasRole(Perfil.ADMIN) && ! id.equals(user.getId())){
            throw  new AuthorizationExeception("Acesso negado");
        }
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

    public Cliente finByEmail(String email){
        UserSS user = UserService.authenticated();
        if(user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())){
            throw new AuthorizationExeception("Acesso negado");
        }
        Cliente obj = repo.findByEmail(email);
        if(obj == null){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + user.getId()
                    + ", Tipo: " + Cliente.class.getName());
        }
        return obj;

    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction),orderBy);
        return repo.findAll(pageRequest);
    }
    public Cliente fromDTO(ClienteDTO objDTO){
        return new Cliente(objDTO.getId(),objDTO.getNome(), objDTO.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDTO){
        Cliente cli = new Cliente(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()));
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

    public URI uploadProfilePicture(MultipartFile multipartFile){
        UserSS user = UserService.authenticated();
        if(user == null){
            throw  new AuthorizationExeception("Acesso negado");
        }
        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        String fileName = prefix + user.getId() + ".jpg";
        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "Image");


    }

}
