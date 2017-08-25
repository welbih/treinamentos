package br.com.caelum.notasfiscais.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.notasfiscais.modelo.Usuario;
import br.com.caelum.notasfiscais.tx.Transactional;


public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public boolean existe(Usuario usuario) {
		
		Query query = manager.createQuery("select u from Usuario u where u.login = :pLogin and u.senha = :pSenha")
						.setParameter("pLogin", usuario.getLogin())
						.setParameter("pSenha", usuario.getSenha());

		boolean encontrado = !query.getResultList().isEmpty();

		return encontrado;
	}
	
	@Transactional
	public void adiciona(Usuario usuario) {
		
		manager.merge(usuario);
	}
	
	public void remove(Usuario usuario) {
		usuario = manager.find(Usuario.class, usuario.getId());
		manager.remove(usuario);
		manager.flush();
	}
	
	public List<Usuario> todos() {
		return manager.createQuery("select u from Usuario u", Usuario.class).getResultList();
	}
}