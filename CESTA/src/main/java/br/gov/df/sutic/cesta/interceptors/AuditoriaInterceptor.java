package br.gov.df.sutic.cesta.interceptors;

import br.gov.df.sutic.cesta.entities.AbstractEntity;
import br.gov.df.sutic.cesta.entities.Operacao;
import br.gov.df.sutic.cesta.persistence.OperacaoFacade;
import java.util.Date;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Interceptador de auditoria.
 * @author Bruno Santos
 */
abstract public class AuditoriaInterceptor
{
    @Inject private OperacaoFacade of;
    
    //<editor-fold defaultstate="collapsed" desc="Gettes/Setters">
    /**
     * Retorna a unidade de persistência de operações.
     * @return persistência de operações
     */
    public OperacaoFacade getOf()
    {
        return of;
    }
    //</editor-fold>
    /**
     * Audita uma operação.
     * @param entidade entidade participante da operação
     * @param tipo tipo de operação
     * @throws Exception exceção passível de ocorrer durante o processo
     */
    public void auditar(AbstractEntity entidade, Operacao.Tipo tipo) throws
            Exception
    {
        Operacao operacao = new Operacao();
        if (FacesContext.getCurrentInstance() != null)
        {
            ExternalContext ec = FacesContext.getCurrentInstance().
                    getExternalContext();
            operacao.setIp(((HttpServletRequest) ec.getRequest()).
                    getRemoteAddr());
            operacao.setNavegador(ec.getRequestHeaderMap().get("User-Agent"));
        }
        operacao.setResponsavel("Sistema");
        operacao.setEntidade(entidade);
        operacao.setHorario(new Date());
        operacao.setTipo(tipo);
        getOf().create(operacao);
    }
}