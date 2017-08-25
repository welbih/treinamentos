package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Model;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;

//@Named
//@ViewScoped
@Model
@ConversationScoped
public class NotaFiscalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private NotaFiscal notaFiscal = new NotaFiscal();
	private Item item = new Item();
	private Long idProduto;

	private HtmlDataTable tabela;

	@Inject
	private Conversation convertion;
	
	@Inject
	private NotaFiscalDao dao;

	@Inject
	private ProdutoDao produtoDao;

	private List<NotaFiscal> notas;

	public String avancar() {
		System.out.println("Verificando conversation : " + convertion.isTransient());
		if(convertion.isTransient()) {
			convertion.begin();
		}
		System.out.println("Passando pelo avancar");
		return "item?faces-redirect=true";
	}
	
	public String gravar() {
		dao.adiciona(notaFiscal);
		convertion.end();
		//this.notaFiscal = new NotaFiscal();
		return "notafiscal?faces-redirect=true";
	}
	
	public void removeItem() {
		Item item = (Item) tabela.getRowData();
		notaFiscal.getItens().remove(item);
	}

	public void guardaItem() {
		Produto produto = produtoDao.buscaPorId(idProduto);
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());

		notaFiscal.getItens().add(item);
		item.setNotaFiscal(notaFiscal);

		item = new Item();
		idProduto = null;
	}

	public List<NotaFiscal> getNotas() {
		notas = dao.listaTodos();
		return notas;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return item;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public HtmlDataTable getTabela() {
		return tabela;
	}

	public void setTabela(HtmlDataTable tabela) {
		this.tabela = tabela;
	}

}
