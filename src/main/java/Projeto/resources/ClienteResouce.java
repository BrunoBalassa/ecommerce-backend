package Projeto.resources;

import Projeto.domain.Cliente;
import Projeto.dto.ClienteDTO;
import Projeto.dto.ClienteNewDTO;
import Projeto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResouce {
        @Autowired
        private ClienteService service;
        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
            public ResponseEntity<Cliente> find(@PathVariable Integer id) {
                Cliente obj= service.find(id);
                return ResponseEntity.ok().body(obj);

        }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert (@Valid @RequestBody ClienteNewDTO objDto){
        Cliente obj = service.fromDTO(objDto);
        obj = service.update(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
        Cliente obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value = "/email", method = RequestMethod.GET)
        public ResponseEntity<Object> find(@RequestParam(value = "value") String email){
            Cliente obj = service.finByEmail(email);
                    return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();


    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDto = list.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);

    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0")  Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> list = service.findPage(page, linesPerPage,orderBy,direction);
        Page<ClienteDTO> listDto = list.map(x -> new ClienteDTO(x));
        return ResponseEntity.ok().body(listDto);
    }
    @RequestMapping(value = "/picture",method = RequestMethod.POST)
    public ResponseEntity<Void> uploadFile (@RequestParam(name = "file") MultipartFile multipartFile){
            URI uri = service.uploadProfilePicture(multipartFile);
              return ResponseEntity.created(uri).build();
    }
}
