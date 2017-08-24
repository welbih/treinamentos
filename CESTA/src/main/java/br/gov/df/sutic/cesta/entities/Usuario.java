package br.gov.df.sutic.cesta.entities;

import br.gov.df.sutic.cesta.enums.Sexo;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author bruno.santos
 */
@Entity
public class Usuario extends AbstractEntity
{
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private boolean ativo;
    @ElementCollection
    private Set<String> apelidos;
    private String cpf, nome, senha, telefone;
    
    public Usuario()
    {
        setApelidos(new HashSet<>());
    }
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    /**
     * @return the ativo
     */
    public boolean isAtivo()
    {
        return ativo;
    }
    /**
     * @return the cpf
     */
    public String getCpf()
    {
        return cpf;
    }
    /**
     * @return the nome
     */
    public String getNome()
    {
        return nome;
    }
    /**
     * @return the senha
     */
    public String getSenha()
    {
        return senha;
    }
    /**
     * @return the telefone
     */
    public String getTelefone()
    {
        return telefone;
    }
    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }
    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }
    /**
     * @param nome the nome to set
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha)
    {
        this.senha = senha;
    }
    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }
    /**
     * @return the sexo
     */
    public Sexo getSexo()
    {
        return sexo;
    }
    /**
     * @param sexo the sexo to set
     */
    public void setSexo(Sexo sexo)
    {
        this.sexo = sexo;
    }
    /**
     * @return the apelidos
     */
    public Set<String> getApelidos()
    {
        return apelidos;
    }
    /**
     * @param apelidos the apelidos to set
     */
    public void setApelidos(Set<String> apelidos)
    {
        this.apelidos = apelidos;
    }
    //</editor-fold>
    
    //@OneToMany
    public void adicionarApelido(String apelido)
    {
        getApelidos().add(apelido);
    }
    public void removerApelido(String apelido)
    {
        getApelidos().remove(apelido);
    }
}