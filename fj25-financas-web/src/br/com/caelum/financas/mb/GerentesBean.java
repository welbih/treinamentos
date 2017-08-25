package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.GerenteDao;
import br.com.caelum.financas.modelo.Gerente;

@Named
@ViewScoped
public class GerentesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GerenteDao dao;

	private List<Gerente> gerentes;

	private Gerente gerente = new Gerente();

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}
	
	public void grava() {
		if(gerente.getId() == null) {
			System.out.println("Gravando um gerente");
			dao.adiciona(gerente);			
		} else {
			System.out.println("Alterando gerente");
			dao.altera(gerente);
		}
		this.gerentes = dao.lista();
		this.limpaFormulario();
	}
	
	public void remove() {
		System.out.println("Removendo um gerente");
		dao.remove(this.gerente);
		this.gerentes = dao.lista();
		
		this.limpaFormulario();
	}
	
	private void limpaFormulario() {
		gerente = new Gerente();
	}
	
	public List<Gerente> getGerentes() {
		if(this.gerentes == null) {
			this.gerentes = dao.lista();
		}
		return gerentes;
	}

}
