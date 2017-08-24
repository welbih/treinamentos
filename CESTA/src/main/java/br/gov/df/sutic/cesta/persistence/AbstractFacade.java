package br.gov.df.sutic.cesta.persistence;

import br.gov.df.sutic.cesta.annotations.Auditoria;
import br.gov.df.sutic.cesta.entities.Operacao;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

/**
 * Modelo abstrato de classes de persistência.
 * @author Bruno Santos
 * @param <T> classe da entidade
 */
abstract public class AbstractFacade<T>
{
    private Class<T> entityClass;

    @Auditoria(tipo = Operacao.Tipo.CRIACAO)
    public void create(T entity)
    {
        getEntityManager().persist(entity);
    }
    @Auditoria(tipo = Operacao.Tipo.EDICAO)
    public void edit(T entity)
    {
        getEntityManager().merge(entity);
    }
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    /**
     * Define a classe da entidade gerenciada pela classe.
     * @param entityClass classe da entidade
     */
    private void setEntityClass(Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }
    /**
     * Retorna a classe da entidade.
     * @return classe da entidade
     */
    public Class<T> getEntityClass()
    {
        return entityClass;
    }
    //</editor-fold>
    /**
     * Retorna o gerenciador de entidade.
     * @return gerenciador de entidade
     */
    protected abstract EntityManager getEntityManager();
    /**
     * Retorna se um registro já existe baseado em um atributo.
     * @param attribute atributo do registro a verificar
     * @param object objeto a procurar no atributo
     * @return false caso não haja um registro com o dado informado ou true caso
     * haja
     */
    protected boolean exists(Object object, SingularAttribute attribute)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(getEntityClass());
        cq.select(cb.count(root)).where(cb.equal(root.get(attribute), object));
        return ((Long) getEntityManager().createQuery(cq).getSingleResult()) > 
                0;
    }
    /**
     * Encontra um registro baseado em parâmetros informados.
     * @param queries parâmetros da busca
     * @return lista de objetos que atendam as limitações
     */
    protected List<T> findBy(Map<Object, SingularAttribute> queries)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(getEntityClass());
        Set<Predicate> predicates = new HashSet<>();
        queries.entrySet().forEach((entry) ->
        {
            predicates.add(cb.equal(root.get(entry.getValue()), entry.
                    getKey()));
        });
        cq.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
        return getEntityManager().createQuery(cq).getResultList();
    }
    /**
     * Encontra registros baseado em um parâmetro.
     * @param attribute atributo do registro a verificar
     * @param object objeto a procurar no atributo
     * @return lista de objetos que possuem no atributo indicado o objeto
     * informado
     */
    protected List<T> findBy(Object object, SingularAttribute attribute)
    {
        Map queries = new HashMap<>();
        queries.put(object, attribute);
        return findBy(queries);
    }
    /**
     * Instancia uma unidade de persistência com a classe informada.
     * @param entityClass classe da entidade
     */
    public AbstractFacade(Class<T> entityClass)
    {
        setEntityClass(entityClass);
    }
    /**
     * Retorna a quantidade de registros na base de dados.
     * @return quantidade de registros
     */
    public int count()
    {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(getEntityManager().getCriteriaBuilder().count(cq.
                from(getEntityClass())));
        return ((Long) getEntityManager().createQuery(cq).getSingleResult()).
                intValue();
    }
    /**
     * Recupera todos os dados da base.
     * @return lista de objetos encontrados
     */
    public List<T> findAll()
    {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(getEntityClass()));
        return getEntityManager().createQuery(cq).getResultList();
    }
    /**
     * Encontra os registros presentes na faixa informada.
     * @param range faixa de objetos desejada
     * @return lista de objetos encontrados
     */
    public List<T> findRange(int[] range)
    {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(getEntityClass()));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    /**
     * Encontra o objeto com o ID informado.
     * @param id ID do objeto
     * @return objeto encontrado
     */
    public T find(Object id)
    {
        return getEntityManager().find(getEntityClass(), id);
    }
    /**
     * Remove a entidade da base de dados.
     * @param entity entidade a ser removida
     */
    public void remove(T entity)
    {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
}