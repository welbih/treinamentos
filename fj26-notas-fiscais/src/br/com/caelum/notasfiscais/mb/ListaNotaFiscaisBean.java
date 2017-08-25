package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.datamodel.DataModelNotaFicais;

@Named
@ViewScoped
public class ListaNotaFiscaisBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private DataModelNotaFicais dataModel;
	
	public DataModelNotaFicais getDataModel() {
		return dataModel;
	}
	
}
