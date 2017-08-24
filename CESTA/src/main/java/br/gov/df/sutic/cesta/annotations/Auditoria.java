package br.gov.df.sutic.cesta.annotations;

import br.gov.df.sutic.cesta.entities.Operacao;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.interceptor.InterceptorBinding;

/**
 * Anotação para métodos que necessitam de auditoria.
 * @author Bruno Santos
 */
@Inherited
@InterceptorBinding
@Retention(RUNTIME)
@Target(
{
    METHOD, TYPE
})
public @interface Auditoria
{
    Operacao.Tipo tipo();
}