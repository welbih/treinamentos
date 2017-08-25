package br.gov.df.sutic.cesta.persistence;

import br.gov.df.sutic.cesta.entities.Agendamento;
import br.gov.df.sutic.cesta.entities.Agendamento_;
import br.gov.df.sutic.cesta.entities.Usuario;
import br.gov.df.sutic.cesta.entities.Usuario_;
import br.gov.df.sutic.cesta.enums.Sexo;
import br.gov.df.sutic.cesta.enums.Turno;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author welber.fernandes
 */
@Stateless
public class AgendamentoFacade extends AbstractFacade<Agendamento>
{
    @PersistenceContext
    private EntityManager em;
    
    public AgendamentoFacade()
    {
        super(Agendamento.class);
    }
    @Override
    protected EntityManager getEntityManager()
    {
        return getEm();
    }
    /**
     * @return the em
     */
    public EntityManager getEm()
    {
        return em;
    }
    public Collection<Agendamento> find(Turno turno)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(getEntityClass());
        cq.select(root).where(cb.equal(root.get(Agendamento_.turno), turno));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public boolean contemUsuario(Usuario usuario) {
        return exists(usuario, Agendamento_.usuario);
    }
}