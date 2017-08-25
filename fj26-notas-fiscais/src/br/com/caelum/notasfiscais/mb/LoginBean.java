package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;
import br.com.caelum.notasfiscais.util.TransactionModel;

//@Named
//@RequestScoped
@Model
public class LoginBean implements Serializable{


	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	
	@Inject
	Event<Usuario> eventoLogin;
	
	@Inject
	private UsuarioDao dao;
	
	@Inject
	private UsuarioLogadoBean usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuaLogin() {
		
		
		boolean loginValido = dao.existe(usuario);
		System.out.println("O login é valido? " + loginValido);
		
		if(loginValido) {
			usuarioLogado.logar(usuario);
			eventoLogin.fire(usuario);
			return "produto?faces-direct=true";
		} else {
			usuarioLogado.deslogar();
			this.usuario = new Usuario();
			return "login";
		}
		
	}
	
	public String deslogaUsuario() {
		if(usuarioLogado.isLogado()) {
			usuarioLogado.deslogar();
			return "login?faces-direct=true";
		}
		return"";
	}
	
}
