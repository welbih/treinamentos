/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.sutic.cesta.controllers;

import br.gov.df.sutic.cesta.enums.Sexo;
import br.gov.df.sutic.cesta.enums.Turno;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author welber.fernandes
 */
@Named
@ApplicationScoped
public class EnunsController implements Serializable{
    
    public Collection<Sexo> getSexo() {
        return Arrays.asList(Sexo.values());
    }
    
    public Turno[] getTurno() {
        return Turno.values();
    }
    
}
