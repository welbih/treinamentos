package br.com.caelum.financas.modelo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.engine.jdbc.ColumnNameCache;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class Gerente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//	@EmbeddedId
//	private GerenteId id;
	
	private String nome;
	private String telefone;
	
	@Embedded
	private Endereco endereco = new Endereco();

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="rua", column=@Column(name="rua_alternativa")),
		@AttributeOverride(name="cidade", column=@Column(name="cidade_alternativa")),
		@AttributeOverride(name="estado", column=@Column(name="estado_alternativa"))
	})
	private Endereco enderecoAlternativo = new Endereco();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
