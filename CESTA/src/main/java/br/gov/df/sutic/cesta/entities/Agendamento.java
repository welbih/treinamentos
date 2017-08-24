/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.sutic.cesta.entities;

import br.gov.df.sutic.cesta.enums.Turno;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author welber.fernandes
 */
@Entity
public class Agendamento extends AbstractEntity{
    
    @ManyToOne
    private Usuario usuario;
    private Turno turno;
    
    @Column(columnDefinition = "text")
    private String motivo;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
}
