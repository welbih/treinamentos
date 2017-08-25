package br.com.caelum.notasfiscais.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.mb.UsuarioLogadoBean;

public class Autorizador implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		
		System.out.println("Passando pelo Autorizador");
		FacesContext context = event.getFacesContext();
		

		if("/login.xhtml".equals(context.getViewRoot().getViewId())){
			System.out.println("Já está em login...");
			return;
		}
		
//		if("/notafiscal.xhtml".equals(context.getViewRoot().getViewId())){
//			System.out.println("Já está em login...");
//			return;
//		}
//		
//		if("/listanotas.xhtml".equals(context.getViewRoot().getViewId())){
//			System.out.println("Já está em login...");
//			return;
//		}
		
		if("/index.xhtml".equals(context.getViewRoot().getViewId())) {
			return;
		}
		
		if(!usuarioLogado.isLogado()) {

			NavigationHandler handler = context.getApplication()
					.getNavigationHandler();
			
			
			handler.handleNavigation(context, null, 
					"login?faces-redirect=true");
			
			System.out.println("Redirecionando para Login");
			context.renderResponse();
		}		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
