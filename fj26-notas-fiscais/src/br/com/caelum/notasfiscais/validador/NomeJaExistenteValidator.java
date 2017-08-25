package br.com.caelum.notasfiscais.validador;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@FacesValidator("nomeJaExistente")
public class NomeJaExistenteValidator implements Validator, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@Override
	public void validate(FacesContext context, UIComponent com, Object obj) throws ValidatorException {
		
		Query q = manager.createQuery("select count(p) " +
				" from Produto p where p.nome like :nome");
		
		q.setParameter("nome", String.valueOf(obj));
		
		Long count = (Long) q.getSingleResult();
		
		if (count != 0) {
			throw new ValidatorException(
					new FacesMessage("Nome de Produto já Existente"));
		}
	}
}
