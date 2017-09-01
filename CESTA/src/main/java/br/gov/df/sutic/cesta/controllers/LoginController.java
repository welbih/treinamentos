/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.sutic.cesta.controllers;

import br.gov.df.sutic.cesta.entities.Usuario;
import br.gov.df.sutic.cesta.persistence.UsuarioFacade;
import java.io.Serializable;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author welber.fernandes
 */
@Model
public class LoginController implements Serializable{
    
    private Usuario usuario;
    
    @Inject
    private UsuarioFacade usuarioFacade;
    
    @Inject
    private UsuarioLogadoController ulc;

    public LoginController() {
        setUsuario(new Usuario());
    }
    
    public String efetuaLogin() {
        boolean loginValido = usuarioFacade.existe(getUsuario());
        
        if(loginValido) {
            getUlc().logar(getUsuario());
            System.out.println(getUlc().isLogado());
            return "listaAgendamentos?faces-redirect";
        } else {
            getUlc().deslogar();
            setUsuario(new Usuario());
            return "login";
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public UsuarioLogadoController getUlc() {
        return ulc;
    }
    
}
