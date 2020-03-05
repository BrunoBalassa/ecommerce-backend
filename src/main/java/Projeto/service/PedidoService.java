package Projeto.service;

import Projeto.domain.*;
import Projeto.domain.enums.EstadoPagamento;
import Projeto.repositories.*;
import Projeto.service.execptions.ObjectNotFoundException;
import ch.qos.logback.core.net.SyslogOutputStream;
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

     @Autowired
     private ClienteService clienteService;

     @Autowired
     private EmailService emailService;

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
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
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
            ip.setProduto((produtoService.find(ip.getProduto().getId())));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedido.saveAll(obj.getItens());
        emailService.sendOrderConfirmationEmail(obj);

        return obj;
    }


}
