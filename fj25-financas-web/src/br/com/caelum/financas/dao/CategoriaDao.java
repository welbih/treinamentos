package br.com.caelum.financas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.financas.modelo.Categoria;

@Stateless
public class CategoriaDao {

	@Inject //@PersistenceContext
	private EntityManager manager;
	
	public Categoria procura(Integer id) {
		manager.joinTransaction();
		return manager.find(Categoria.class, id);
	}
	
	public List<Categoria> lista() {
		return manager.createQuery("select c from Categoria c", Categoria.class)
					.getResultList();	
	}
	
}
