/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.sutic.cesta.controllers;

import br.gov.df.sutic.cesta.entities.Agendamento;
import br.gov.df.sutic.cesta.entities.Usuario;
import br.gov.df.sutic.cesta.enums.Turno;
import br.gov.df.sutic.cesta.persistence.AgendamentoFacade;
import br.gov.df.sutic.cesta.persistence.UsuarioFacade;
import br.gov.df.sutic.cesta.web.JSF;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author welber.fernandes
 */
@Named
@ViewScoped
public class AgendamentoController implements Serializable{
    
    private Agendamento agendamento;
    
    private List<Usuario> usuarios;
    
    private List<Agendamento> agendamentos;
    
    private Collection<Agendamento> agendamentoPorTurno;
    
    private Long idUsuario;
    
    private Turno turno;
    
    private Long idAgendamento;
    
    @Inject
    private UsuarioFacade usuarioFacade;

    @Inject
    private AgendamentoFacade agendamentoFacade;
    
    /**
     * construtor chamando setters 
     * de alguns atributos
     */
    public AgendamentoController() {
        setAgendamento(new Agendamento());
        setUsuarios(new ArrayList<>());
        setIdUsuario(Long.MIN_VALUE);
        setAgendamentos(new ArrayList<>());
        setAgendamentoPorTurno(new ArrayList<>());
    }
    
    /**
     * instancia um novo agendamento
     * se a pagina não estiver sendo usada para editar
     */
    public void inicializar() {
        setIdUsuario(getAgendamento().getUsuario().getId());   
    }
    
    /**
     * salva um novo agendamento
     * ou 
     * edita o agendamento solicidado.
     * @return lista de agendamentos
     */
    public String agendar() {
        getAgendamento().setUsario(getUsuarioFacade().find(getIdUsuario()));
        getAgendamentoFacade().create(agendamento);
        JSF.addSuccessMessage("Agendamento realizado com sucesso!");
        
        return "listaAgendamentos";
    }
    
    /**
     * salva agendamento editado
     * @return lista agendamentos
     */
    public String editar() {
        getAgendamento().setUsario(getUsuarioFacade().find(getIdUsuario()));
        getAgendamentoFacade().edit(agendamento);
        JSF.addSuccessMessage("Agendamento editado com sucesso!");
        
        return "listaAgendamentos";
    }
    
    /**
     * remove um agendamento da lista
     * @param agendamento 
     */
    public void removerAgendamento(Agendamento agendamento) {
        System.out.println("Removendo agendamento...");
        getAgendamentoFacade().remove(agendamento);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public List<Usuario> getUsuarios() {
        return getUsuarioFacade().findAll();
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public AgendamentoFacade getAgendamentoFacade() {
        return agendamentoFacade;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Agendamento> getAgendamentos() {
        return getAgendamentoFacade().findAll();
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public Collection<Agendamento> getAgendamentoPorTurno() {
        return getAgendamentoFacade().find(getTurno());
    }

    public void setAgendamentoPorTurno(Collection<Agendamento> agendamentoPorTurno) {
        this.agendamentoPorTurno = agendamentoPorTurno;
    }
    
    public Turno getTurno() {
        return turno;
    }
    
    public void setTurno(Turno turno) {
        this.turno = turno;
    }
    
    //</editor-fold>



}
