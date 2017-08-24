package br.gov.df.sutic.cesta.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Entidade abstrata.
 * @author Bruno Santos
 */
@MappedSuperclass
abstract public class AbstractEntity implements Serializable
{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private static final long serialVersionUID = 1L;
    
    @Override
    public boolean equals(Object object)
    {
        return object instanceof AbstractEntity && Objects.equals(getId(),
                ((AbstractEntity) object).getId());
    }
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(getId());
        return hash;
    }
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    /**
     * Retorna o ID da entidade.
     * @return ID
     */
    public Long getId()
    {
        return id;
    }
    /**
     * Retorna o ID de versão serial.
     * @return ID de versão serial
     */
    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }
    /**
     * Define o ID da entidade.
     * @param id ID da entidade
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    //</editor-fold>
}