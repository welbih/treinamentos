package br.com.caelum.financas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Gerente;
import br.com.caelum.financas.modelo.GerenteConta;

@Stateless
public class GerenteDao {

	@Inject
	private EntityManager manager;
	
	public void adiciona(Gerente g) {
		manager.joinTransaction();
		manager.persist(g);
	}
	
	public void remove(Gerente g) {
		manager.joinTransaction();
		g = manager.find(Gerente.class, g.getId());
		manager.remove(g);
		manager.flush();
	}
	
	public GerenteConta busca(Integer id) {
		manager.joinTransaction();
		return manager.find(GerenteConta.class, id);
	}
	
	public List<Gerente> lista() {
		return manager.createQuery("select g from Gerente g", Gerente.class).getResultList();
	}
	
	public void altera(Gerente g) {
		manager.joinTransaction();
		manager.merge(g);
	}
	
}
