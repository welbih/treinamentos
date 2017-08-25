package br.com.caelum.notasfiscais.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class DaoFactory implements Serializable{

	private static final long serialVersionUID = 1L;

	@Produces
	public <T> Dao<T> createDao(InjectionPoint injectionPoint) {
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
		Class classe = (Class) type.getActualTypeArguments()[0];
		System.out.println("Class: " + classe.getName());
		return new Dao(classe); 
	}
}
