package Projeto.service;

import Projeto.domain.*;
import Projeto.domain.enums.EstadoPagamento;
import Projeto.repositories.*;
import Projeto.service.execptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedido;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamento;

     @Autowired
     private ProdutoService produtoService;

     @Autowired
     private ItemPedidoRepository itemPedido;

    public Pedido buscar(Integer id){
          Optional<Pedido> obj = pedido.findById(id);
          return obj.orElseThrow(() ->
                  new ObjectNotFoundException("Objeto não encontrado! Id: "+ id + ", Tipo " +
                          Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj){
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if(obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());

        }
        obj = pedido.save(obj);
        pagamento.save(obj.getPagamento());
        for(ItemPedido ip : obj.getItens()){
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }
        itemPedido.saveAll(obj.getItens());
        return obj;
    }


}
