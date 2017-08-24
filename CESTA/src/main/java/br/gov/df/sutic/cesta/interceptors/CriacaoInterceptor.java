package br.gov.df.sutic.cesta.interceptors;

import br.gov.df.sutic.cesta.annotations.Auditoria;
import br.gov.df.sutic.cesta.entities.AbstractEntity;
import br.gov.df.sutic.cesta.entities.Operacao;
import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Interceptador de criação de registros.
 * @author Bruno Santos
 */
@Auditoria(tipo = Operacao.Tipo.CRIACAO) @Interceptor @Priority(Integer.
        MAX_VALUE)
public class CriacaoInterceptor extends AuditoriaInterceptor
{
    @AroundInvoke
    public Object aoCriar(InvocationContext ic) throws Exception
    {
        try
        {
            return ic.proceed();
        }
        finally
        {
            if (ic.getParameters()[0] instanceof AbstractEntity)
                auditar((AbstractEntity) ic.getParameters()[0], Operacao.Tipo.
                    CRIACAO);
        }
    }
}