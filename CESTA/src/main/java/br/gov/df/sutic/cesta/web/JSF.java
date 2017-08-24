package br.gov.df.sutic.cesta.web;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.ProjectStage;
import javax.faces.context.FacesContext;

/**
 * Essa classe contém apenas métodos estáticos de gerenciamento do JSF.
 * @author Bruno Santos
 */
public class JSF
{
    /**
     * Retorna se há erros no contexto do JSF.
     * @return false caso não haja erros ou true caso haja
     */
    public static boolean hasErrors()
    {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }
    /**
     * Retorna um parâmetro associado a uma determinada chave.
     * @param key chave do parâmetro
     * @return parâmetro
     */
    public static Object getRequestParameter(String key)
    {
        return FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get(key);
    }
    /**
     * Retorna o estágio do projeto.
     * @return estágio do projeto
     */
    public static ProjectStage getProjectStage()
    {
        return FacesContext.getCurrentInstance().getApplication().
                getProjectStage();
    }
    /**
     * Adiciona uma mensagem de erro ao contexto.
     * @param message mensagem de erro
     */
    public static void addErrorMessage(String message)
    {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                message, message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    /**
     * Adiciona uma lista de mensagens de erro ao contexto.
     * @param messages mensagens de erro
     */
    public static void addErrorMessages(List<String> messages)
    {
        for (String message : messages)
            addErrorMessage(message);
    }
    /**
     * Adiciona uma mensagem de sucesso ao contexto.
     * @param message mensagem de texto
     */
    public static void addSuccessMessage(String message)
    {
        addSuccessMessage(message, message);
    }
    /**
     * Adiciona uma mensagem de sucesso ao contexto.
     * @param detail detalhe da mensagem
     * @param message mensagem de texto
     */
    public static void addSuccessMessage(String detail, String message)
    {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                message, detail);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
    /**
     * Adiciona uma mensagem de erro de validação com o contexto atual.
     * @param message mensagem de erro
     */
    public static void addValidationError(String message)
    {
        addValidationError(FacesContext.getCurrentInstance(), message);
    }
    /**
     * Adiciona uma mensagem de erro de validação.
     * @param fc contexto 
     * @param message mensagem de erro
     */
    public static void addValidationError(FacesContext fc, String message)
    {
        addErrorMessage(message);
        fc.validationFailed();
    }
    /**
     * Adiciona uma mensagem de erro de validação.
     * @param exception exceção
     * @param padrao mensagem padrão caso não se encontre uma localizada
     */
    public static void ensureAddErrorMessage(Exception exception, String padrao)
    {
        String msg = exception.getLocalizedMessage();
        if (msg != null && msg.length() > 0)
            addErrorMessage(msg);
        else
            addErrorMessage(padrao);
    }
    /**
     * Define um parâmetro com um par de chave/objeto.
     * @param key chave do parâmetro
     * @param value valor do parâmetro
     */
    public static void setRequestParameter(String key, Object value)
    {
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().
                put(key, value);
    }
}