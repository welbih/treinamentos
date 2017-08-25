package br.com.caelum.notasfiscais.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.util.ComponentResolver;

@FacesValidator("nomeEDescricao")
public class ValidadorNomeEDescricao implements Validator{

	@Inject
	private ComponentResolver resolver;
	
	@Override
	public void validate(FacesContext ctx, UIComponent com, Object obj) throws ValidatorException {
		
		String nome = resolver.getSubmittedValue("nome");
		String descricao = resolver.getSubmittedValue("descricao");
		
		System.out.printf("Nome %s Descricao %s %n", nome, descricao);
		
		if(nome != null && descricao != null && nome.equals(descricao)) {
			throw new ValidatorException(
					new FacesMessage("Nome e descrição não podem ser iguais"));
		}
		
	}

}
