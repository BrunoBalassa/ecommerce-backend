package Projeto;

import Projeto.domain.*;
import Projeto.domain.enums.EstadoPagamento;
import Projeto.domain.enums.TipoCliente;
import Projeto.repositories.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ProjetoApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository repo;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository er;

	@Autowired
	private CidadeRepository cr;

	@Autowired
	private ClienteRepository cle;
	@Autowired
	private EnderecoRepository en;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {


//		Categoria cat1 = new Categoria(null, "Informatica");
//		Categoria cat2 = new Categoria(null, "Escritorio");
//
//		Produto pro1 = new Produto(null, "Computador",2000.00);
//		Produto pro2 = new Produto(null, "Impressora",800.00);
//		Produto pro3 = new Produto(null, "Mouse",80.00);
//
//
//		cat1.getProdutos().addAll(Arrays.asList(pro1,pro3, pro2));
//		cat2.getProdutos().addAll(Arrays.asList(pro2));
//		pro1.getCategorias().addAll(Arrays.asList(cat1));
//		pro2.getCategorias().addAll(Arrays.asList(cat1,cat2));
//		pro3.getCategorias().addAll(Arrays.asList(cat1));
//
//		repo.saveAll(Arrays.asList(cat1,cat2));
//		produtoRepository.saveAll(Arrays.asList(pro1,pro2,pro3));
//
//
//		Estado est1 = new Estado(null, "Minas Gerais");
//		Estado est2 = new Estado(null, "São Paulo");
//		Cidade c1 = new Cidade(null, "Uberlandia", est1);
//		Cidade c2 = new Cidade(null, "São paulo", est2);
//		Cidade c3 = new Cidade(null, "Campinas", est2);
//
//		est1.getCidades().addAll(Arrays.asList(c1));
//		est2.getCidades().addAll(Arrays.asList(c2, c3));
//
//
//		er.saveAll(Arrays.asList(est1,est2));
//		cr.saveAll(Arrays.asList(c1,c2,c3));
//
//		Cliente cliente = new Cliente(null, "Maria silva", "maria@gmail", "09744157909", TipoCliente.PESSOAFISICA);
//		cliente.getTelefones().addAll(Arrays.asList("97098859","88121203"));
//
//		Endereco e1 = new Endereco(null,"R: flores", "300","apto 303","Jardim","3380215161",cliente,c1);
//		Endereco e2 = new Endereco(null, "Rua cascavel", "3124", "apto 21", "Boqueirao", "81750090", cliente, c2);
//
//		cliente.getEnderecos().addAll(Arrays.asList(e1,e2));
//
//		cle.saveAll(Arrays.asList(cliente));
//		en.saveAll(Arrays.asList(e1,e2));
//
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");
//		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente, e1 );
//		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cliente, e2);
//
//		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
//		ped1.setPagamento(pagto1);
//
//		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
//		ped2.setPagamento(pagto2);
//
//		cliente.getPedidos().addAll(Arrays.asList(ped1,ped2));
//		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
//		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
//
//
//		ItemPedido ip1 = new ItemPedido(ped1, pro1, 0.00, 1,2000.00);
//		ItemPedido ip2 = new ItemPedido(ped1, pro3, 0.0, 2, 80.00);
//		ItemPedido ip3 = new ItemPedido(ped2, pro2, 100.0, 1, 800.00);
//
//		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
//		ped2.getItens().addAll(Arrays.asList(ip3));
//
//		pro1.getItens().addAll(Arrays.asList(ip1));
//		pro2.getItens().addAll(Arrays.asList(ip3));
//		pro3.getItens().addAll(Arrays.asList(ip2));
//

		




	}
}
