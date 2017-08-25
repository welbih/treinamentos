package br.com.caelum.notasfiscais.auditoria;


import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.jboss.logging.Logger;

import br.com.caelum.notasfiscais.mb.UsuarioLogadoBean;

@Interceptor
@Auditavel
public class AuditoriaInterceptor {

	@Inject
	private UsuarioLogadoBean usuarioLogadoBean;
	
	private static final Logger logger = Logger.getLogger(AuditoriaInterceptor.class);
	
	@AroundInvoke
	public Object audita(InvocationContext ic) throws Exception {
		Object result = ic.proceed();
		logger.info("O método: " + ic.getMethod().getName()
					+ " foi executado pelo usuario: "
					+ usuarioLogadoBean.getUsuario().getLogin()
					+ " na data: " + new SimpleDateFormat("dd/MM/yyyy 'às' hh:mm:ss"));
		
		return result;
	}
	
}
