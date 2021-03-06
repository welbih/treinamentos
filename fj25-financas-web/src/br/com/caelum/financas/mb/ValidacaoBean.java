package br.com.caelum.financas.mb;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.com.caelum.financas.modelo.Conta;

import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ValidacaoBean {

	private Conta conta = new Conta();

	@Inject
	private Validator validator;

	public void validar() {
		System.out.println("Validando a conta");

		Set<ConstraintViolation<Conta>> erros = validator.validate(conta);
		for (ConstraintViolation<Conta> erro : erros) {
			geraMensagemJsf(erro);
		}

	}

	public Conta getConta() {
		return conta;
	}

	/**
	 * Esse metodo disponibiliza uma mensagem para a tela JSF.
	 */
	private void geraMensagemJsf(ConstraintViolation<Conta> erro) {
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage(erro.getPropertyPath().toString() + "  " + erro.getMessage()));
	}

}
