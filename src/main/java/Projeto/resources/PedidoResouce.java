package Projeto.resources;

import Projeto.domain.Categoria;
import Projeto.domain.Cliente;
import Projeto.domain.Pedido;
import Projeto.dto.CategoriaDTO;
import Projeto.service.ClienteService;
import Projeto.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResouce {
        @Autowired
        private PedidoService service;
        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
            public ResponseEntity<Pedido> find(@PathVariable Integer id) {
                Pedido obj= service.buscar(id);
                return ResponseEntity.ok().body(obj);

        }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert (@Valid @RequestBody Pedido obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<Pedido>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="instante") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction) {
        Page<Pedido> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }
}
