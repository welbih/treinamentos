/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.sutic.cesta.controllers;

import br.gov.df.sutic.cesta.entities.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author welber.fernandes
 */
@Named
@SessionScoped
public class UsuarioLogadoController implements Serializable{
    
    private Usuario usuario;

    public UsuarioLogadoController() {
        setUsuario(new Usuario());
        System.out.println("Passando pelo construtor");
    }

    public void logar(Usuario usuario) {
        setUsuario(usuario);
    }
    
    public void deslogar() {
        setUsuario(new Usuario());
    }
    
    public boolean isLogado() {
        System.out.println("Está estranho essa parada");
        return getUsuario() != null;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
