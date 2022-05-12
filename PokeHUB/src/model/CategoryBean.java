package model;

import java.io.Serializable;

public class CategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private String nomeCategoria;
	
	
	
	public CategoryBean() {
		nomeCategoria = "";	
	}

	
	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}


	@Override
	public String toString() {
		return "CategoryBean [nomeCategoria=" + nomeCategoria + "]";
	}

	
}
