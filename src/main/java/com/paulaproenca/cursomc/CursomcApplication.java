package com.paulaproenca.cursomc;

import com.paulaproenca.cursomc.domain.*;
import com.paulaproenca.cursomc.domain.enums.TipoCliente;
import com.paulaproenca.cursomc.domain.enums.EstadoPagamentoEnum;
import com.paulaproenca.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria informatica = new Categoria(null, "Informatica");
		Categoria escritorio = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		Estado mg = new Estado(null, "Minas Gerais");
		Estado esp = new Estado(null, "São Paulo");

		Cidade ub = new Cidade(null, "Uberlandia", mg);
		Cidade sp = new Cidade(null, "São Paulo", esp);
		Cidade cm = new Cidade(null, "Campinas", esp);

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "1234568911", TipoCliente.PESSOA_FISICA);
		Endereco e1 = new Endereco(null, "Rua flores", "300", "apto 203", "Jardim", "38220834",ub, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", sp, cli1);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/201 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 00:00"), cli1, e2);

		Pagamento pag1 = new PagamentoCartao(null, EstadoPagamentoEnum.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoBoleto(null, EstadoPagamentoEnum.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pag2);

		ItemPedido ip1 = new ItemPedido(ped1, p1, 00.00,1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 00.00,2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00,1, 800.00);

		informatica.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		escritorio.getProdutos().addAll(List.of(p2));
		p1.getCategorias().addAll(List.of(informatica));
		p2.getCategorias().addAll(Arrays.asList(informatica,escritorio));
		p3.getCategorias().addAll(List.of(informatica));
		mg.getCidades().addAll(Arrays.asList(ub));
		esp.getCidades().addAll(Arrays.asList(sp, cm));
		cli1.getTelefones().addAll(Arrays.asList("1234556123", "789456123"));
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().add(ip3);
		p1.getItens().add(ip1);
		p2.getItens().add(ip3);
		p3.getItens().add(ip2);

		categoriaRepository.saveAll(Arrays.asList(informatica, escritorio));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(mg, esp));
		cidadeRepository.saveAll(Arrays.asList(ub, sp, cm));
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
