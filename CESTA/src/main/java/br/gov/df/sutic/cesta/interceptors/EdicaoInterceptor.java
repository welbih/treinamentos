package br.gov.df.sutic.cesta.interceptors;

import br.gov.df.sutic.cesta.annotations.Auditoria;
import br.gov.df.sutic.cesta.entities.AbstractEntity;
import br.gov.df.sutic.cesta.entities.Operacao;
import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Interceptador de edição de registros.
 * @author Bruno Santos
 */
@Auditoria(tipo = Operacao.Tipo.EDICAO) @Interceptor @Priority(Integer.
        MAX_VALUE)
public class EdicaoInterceptor extends AuditoriaInterceptor
{
    @AroundInvoke
    public Object aoEditar(InvocationContext ic) throws Exception
    {
        try
        {
            return ic.proceed();
        }
        finally
        {
            if (ic.getParameters()[0] instanceof AbstractEntity)
                auditar((AbstractEntity) ic.getParameters()[0], Operacao.Tipo.
                    EDICAO);
        }
    }
}