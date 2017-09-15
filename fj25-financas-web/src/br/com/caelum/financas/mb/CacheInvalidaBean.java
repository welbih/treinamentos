package br.com.caelum.financas.mb;

import br.com.caelum.financas.modelo.Conta;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Cache;

@Named
@RequestScoped
public class CacheInvalidaBean {
	
	private Integer id;
	private Conta conta;

	@Inject
	private Cache cache;
	

	public void invalidar() {
		System.out.println("Invalidando o cache programaticamente");
		cache.evict(Conta.class, getId());
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Conta getConta() {
		return conta;
	}
}
