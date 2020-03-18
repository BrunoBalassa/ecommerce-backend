package Projeto.resources;

import Projeto.domain.Cidade;
import Projeto.domain.Estado;
import Projeto.dto.CidadeDTO;
import Projeto.dto.EstadoDTO;
import Projeto.service.CidadeService;
import Projeto.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/estados")
public class EstadoResouce {
        @Autowired
        private EstadoService service;

        @Autowired
        private CidadeService cidadeService;

        @RequestMapping(method=RequestMethod.GET)
            public ResponseEntity<List<EstadoDTO>> findALL (){
            List<Estado> list = service.findAll();
            List<EstadoDTO> listDTO = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
            return ResponseEntity.ok().body(listDTO);

        }

    @RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTO>>  findCidades(@PathVariable Integer estadoId){
        List<Cidade>listaCidade = cidadeService.findAll(estadoId);
        List<CidadeDTO> obj = listaCidade.stream().map(cidade -> new CidadeDTO(cidade)).collect(Collectors.toList());
        return ResponseEntity.ok().body(obj);
    }

}
