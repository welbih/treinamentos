package br.gov.df.sutic.cesta.persistence;

import br.gov.df.sutic.cesta.entities.Usuario;
import br.gov.df.sutic.cesta.entities.Usuario_;
import br.gov.df.sutic.cesta.enums.Sexo;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author welber.fernandes
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario>
{
    @PersistenceContext
    private EntityManager em;
    
    public UsuarioFacade()
    {
        super(Usuario.class);
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
    public Collection<Usuario> findBySexo(Sexo sexo)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(getEntityClass());
        cq.select(root).where(cb.equal(root.get(Usuario_.sexo), sexo));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
}