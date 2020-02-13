package Projeto.service;

import Projeto.domain.Categoria;
import Projeto.domain.Pedido;
import Projeto.repositories.CategoriaRepository;
import Projeto.repositories.PedidoRepository;
import Projeto.service.execptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedido;
    public Pedido buscar(Integer id){
          Optional<Pedido> obj = pedido.findById(id);
          return obj.orElseThrow(() ->
                  new ObjectNotFoundException("Objeto não encontrado! Id: "+ id + ", Tipo " +
                          Pedido.class.getName()));
    }
}
