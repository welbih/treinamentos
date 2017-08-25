package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;
import br.com.caelum.notasfiscais.util.ViewModel;

//@Named
//@ViewScoped
@ViewModel
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	@Inject
	private UsuarioDao dao;
	
	private List<Usuario> usuarios;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void salva() {
		System.out.println("Salvando usuario...");
		dao.adiciona(usuario);
		usuarios = dao.todos();
		this.usuario = new Usuario();
	}
	
	public List<Usuario> getUsuarios() {
		if(usuarios == null) {
			usuarios = dao.todos();
		}
		return usuarios;
	}
	
	public void remove(Usuario usuario) {
		dao.remove(usuario);
		usuarios = dao.todos();
	}

}
