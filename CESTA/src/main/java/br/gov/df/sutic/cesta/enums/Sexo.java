package br.gov.df.sutic.cesta.enums;

/**
 *
 * @author bruno.santos
 */
public enum Sexo
{
    FEMININO ("Feminino"), MASCULINO ("Masculino");
    
    private String nome;
    
    /**
     * Instancia o enumerador com o nome informado.
     * @param nome nome do sexo
     */
    private Sexo(String nome)
    {
        setNome(nome);
    }
    /**
     * Retorna a nome do sexo.
     * @return nome
     */
    public String getNome()
    {
        return nome;
    }
    /**
     * Define a nome do sexo.
     * @param nome nome a definir
     */
    private void setNome(String nome)
    {
        this.nome = nome;
    }
}