package model;

import java.io.Serializable;

public class UserBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cognome;
	private String mail;
	private String password;
	private String cellulare;
	private String categoriaUtente;
	

	public UserBean() {
		nome = "";
		cognome = "";
		mail = "";
		password = "";
		cellulare = "";
		categoriaUtente = "";
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCellulare() {
		return cellulare;
	}


	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}


	public String getCategoriaUtente() {
		return categoriaUtente;
	}


	public void setCategoriaUtente(String categoriaUtente) {
		this.categoriaUtente = categoriaUtente;
	}


	@Override
	public String toString() {
		return "UserBean [nome=" + nome + ", cognome=" + cognome + ", mail=" + mail + ", password=" + password
				+ ", cellulare=" + cellulare + ", categoriaUtente=" + categoriaUtente + "]";
	}
	
	

		


}
