package br.gov.df.sutic.cesta.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Operação realizada no banco.
 * @author Bruno Santos
 */
@Entity
public class Operacao extends AbstractEntity
{
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date horario;
    private AbstractEntity entidade;
    private String ip, navegador, responsavel;
    
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    /**
     * Retorna a entidade.
     * @return entidade
     */
    public AbstractEntity getEntidade()
    {
        return entidade;
    }
    /**
     * Retorna o horário da operação.
     * @return horário da operação
     */
    public Date getHorario()
    {
        return horario;
    }
    /**
     * Retorna o IP de origem.
     * @return IP de origem
     */
    public String getIp()
    {
        return ip;
    }
    /**
     * Retorna o navegador de origem.
     * @return navegador de origem7
     */
    public String getNavegador()
    {
        return navegador;
    }
    /**
     * Retorna o responsável pela operação.
     * @return responsável pela operação
     */
    public String getResponsavel()
    {
        return responsavel;
    }
    /**
     * Retorna o tipo da operação.
     * @return tipo da operação
     */
    public Tipo getTipo()
    {
        return tipo;
    }
    /**
     * Defina a entidade da operação.
     * @param entidade entidade a definir
     */
    public void setEntidade(AbstractEntity entidade)
    {
        this.entidade = entidade;
    }
    /**
     * Define o horário da operação.
     * @param horario horário a definir
     */
    public void setHorario(Date horario)
    {
        this.horario = horario;
    }
    /**
     * Define o IP de origem.
     * @param ip IP a definir
     */
    public void setIp(String ip)
    {
        this.ip = ip;
    }
    /**
     * Define o navegador de origem.
     * @param navegador navegador de origem
     */
    public void setNavegador(String navegador)
    {
        this.navegador = navegador;
    }
    /**
     * Define o responsável pela operação.
     * @param responsavel responsável pela operação
     */
    public void setResponsavel(String responsavel)
    {
        this.responsavel = responsavel;
    }
    /**
     * Define o tipo da operação.
     * @param tipo tipo a definir
     */
    public void setTipo(Tipo tipo)
    {
        this.tipo = tipo;
    }
    //</editor-fold>
    /**
     * Tipos de operação.
     */
    public static enum Tipo
    {
        CRIACAO, EDICAO, REMOCAO;
    }
}