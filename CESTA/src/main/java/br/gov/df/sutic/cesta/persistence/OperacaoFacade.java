package br.gov.df.sutic.cesta.persistence;

import br.gov.df.sutic.cesta.entities.Operacao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Persistência de operações.
 * @author Bruno Santos
 */
@Stateless
public class OperacaoFacade
{
    @PersistenceContext
    private EntityManager em;
    
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    /**
     * Retorna o gerenciador de entidades.
     * @return gerenciador de entidades
     */
    protected EntityManager getEntityManager()
    {
        return em;
    }
//</editor-fold>
    public void create(Operacao operacao)
    {
        getEntityManager().persist(operacao);
    }
}