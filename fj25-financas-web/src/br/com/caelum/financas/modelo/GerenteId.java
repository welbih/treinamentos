package br.com.caelum.financas.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class GerenteId implements Serializable {

	private String rg;

	private String cpf;

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
