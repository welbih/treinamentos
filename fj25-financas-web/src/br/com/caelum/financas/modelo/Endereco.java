package br.com.caelum.financas.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

	private String rua;
	private String cidade;
	private String estago;

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstago() {
		return estago;
	}

	public void setEstago(String estago) {
		this.estago = estago;
	}

}
