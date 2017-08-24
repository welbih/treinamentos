/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.sutic.cesta.controllers;

import br.gov.df.sutic.cesta.entities.Agendamento;
import br.gov.df.sutic.cesta.entities.Usuario;
import br.gov.df.sutic.cesta.enums.Sexo;
import br.gov.df.sutic.cesta.enums.Turno;
import br.gov.df.sutic.cesta.persistence.AgendamentoFacade;
import br.gov.df.sutic.cesta.persistence.UsuarioFacade;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author welber.fernandes
 */
@Singleton
@Startup
public class InicializadorController {
    
    private Usuario usuario;
    
    private Agendamento agendamento;
    
    @Inject
    private UsuarioFacade usuarioFacade;
    
    @Inject
    private AgendamentoFacade agendamentoFacade;
    
    
    @PostConstruct
    public void popularAgendamentos() {
        System.out.println("populando usuário e agendamento.");
        setUsuario(new Usuario());
        getUsuario().setNome("Fernandes");
        getUsuario().setCpf("12345678911");
        getUsuario().setSenha("456789"); 
        getUsuario().setSexo(Sexo.FEMININO);
        getUsuario().setTelefone("6193309330");
        getUsuario().setAtivo(true);
        getUsuarioFacade().create(getUsuario());
        
        setAgendamento(new Agendamento());
        getAgendamento().setUsario(getUsuario());
        getAgendamento().setTurno(Turno.NOTURNO);
        getAgendamento().setMotivo("Checape geral");
        getAgendamentoFacade().create(getAgendamento());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public AgendamentoFacade getAgendamentoFacade() {
        return agendamentoFacade;
    }
    
}
