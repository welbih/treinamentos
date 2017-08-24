/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.sutic.cesta.converters;

import br.gov.df.sutic.cesta.entities.Agendamento;
import br.gov.df.sutic.cesta.persistence.AgendamentoFacade;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author welber.fernandes
 */
@FacesConverter(forClass = Agendamento.class)
public class AgendamentoConverter implements Converter{

     @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String 
            string)
    {
        if (string == null || string.length() == 0)
            return null;
        Long id = new Long(string);
        return CDI.current().select(AgendamentoFacade.class).get().find(id);
    }
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object 
            object)
    {
        if (object == null)
            return null;
        if (object instanceof Agendamento)
        {
            Agendamento empresa = (Agendamento) object;
            return empresa.getId() == null ? "" : empresa.getId().toString();
        }
        else
            throw new IllegalArgumentException("Objeto " + object + " é do tipo"
                    + " " + object.getClass().getName() + " e não do tipo " +
                    Agendamento.class.getName() + ".");
    }    
    
}
