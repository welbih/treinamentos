package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.auditoria.Auditavel;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

@Model
public class ProdutoBean {

	private Produto produto = new Produto();

	private double totalDosPrecos;

	private Long produtoId;
	
	@Inject
	@Any
	private Instance<String> todosOsEmail;
	
	@Inject
	private ProdutoDao dao;

//	@Inject
//	private Dao<Produto> dao;
	
	private List<Produto> produtos;

	@Auditavel
	public String grava() {
		System.out.println("Produto " + produto.getNome());
		if (produto.getId() == null) {
			dao.adiciona(produto);
		} else {
			dao.atualiza(produto);
		}
		System.out.println("Salvando produto");
		produto = new Produto();
		this.produtos = dao.listaTodos();
		
		for (String email : todosOsEmail) {
			System.out.println("Notifica: " + email);
		}
		
		return "produto?faces-redirect=true";
	}
	
	public void carregaProduto() {
		if(produtoId != null && produtoId != 0) {
			this.produto = dao.buscaPorId(this.produtoId);
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			System.out.println("Listando produto");
			this.produtos = dao.listaTodos();
		}
		return produtos;
	}

	public void remove(Produto produto) {
		System.out.println("Removendo produto");
		dao.remove(produto);
		this.produtos = dao.listaTodos();
	}

	public double getTotalDosPrecos() {
		totalDosPrecos = dao.calculaPrecos();
		return totalDosPrecos;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void cancelaEdicao() {
		System.out.println("Cancelando edição...");
		this.limpaFormulario();
	}

	private void limpaFormulario() {
		this.produto = new Produto();
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

}
