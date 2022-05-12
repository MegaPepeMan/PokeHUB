package model;

import java.io.Serializable;

public class AddressBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idIndirizzo;
	private String mailCliente;
	private String via;
	private String civico;
	private String telefono;
	private String cap;
	
	
	public AddressBean() {
		
		mailCliente = "";
		via = "";
		civico = "";
		telefono = "";
		cap ="";
		
	}

	public int getIdIndirizzo() {
		return idIndirizzo;
	}


	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	public String getMailCliente() {
		return mailCliente;
	}


	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}


	public String getVia() {
		return via;
	}


	public void setVia(String via) {
		this.via = via;
	}


	public String getCivico() {
		return civico;
	}


	public void setCivico(String civico) {
		this.civico = civico;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getCap() {
		return cap;
	}


	public void setCap(String cap) {
		this.cap = cap;
	}


	@Override
	public String toString() {
		return "AddressBean [mailCliente=" + mailCliente + ", via=" + via + ", civico=" + civico + ", telefono="
				+ telefono + ", cap=" + cap + "]";
	}
}
