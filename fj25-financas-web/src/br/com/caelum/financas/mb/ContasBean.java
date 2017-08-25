package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.dao.GerenteDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Gerente;

@Named
@ViewScoped
public class ContasBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

	private Conta conta = new Conta();
	private List<Conta> contas;
	private List<Gerente> gerentes;
	
	private Integer gerenteId;
	
	@Inject
	private ContaDao cDao; 
	
	@Inject
	private GerenteDao gDao;

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public void grava() {
		System.out.println("Gravando a conta");

		if(gerenteId != null) {
			Gerente gerenteRelacionado = gDao.busca(gerenteId);
			this.conta.setGerente(gerenteRelacionado);
		}
		
		if(conta.getId()==null) {
			cDao.adiciona(conta);
		}else {
			cDao.altera(conta);
		}
		this.contas = cDao.lista();			
		limpaFormularioDoJSF();
	}

	public List<Conta> getContas() {
		System.out.println("Listando as contas");
		if(contas == null) {
			this.contas = cDao.lista();
		}
		return contas;
	}

	public void remove() {
		System.out.println("Removendo a conta");
		cDao.remove(this.conta);
		this.contas = cDao.lista();
		
		limpaFormularioDoJSF();
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.conta = new Conta();
	}
	
	public Integer getGerenteId() {
		return gerenteId;
	}
	
	public void setGerenteId(Integer gerenteId) {
		this.gerenteId = gerenteId;
	}
	
	public List<Gerente> getGerentes() {
		if(this.gerentes == null ) {
			this.gerentes = gDao.lista();
		}	
		return gerentes;
	}
}
