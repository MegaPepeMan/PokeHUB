package model;

import java.io.Serializable;
import java.sql.Date;

public class OrderBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int IdOrdine;
	private String TrakingOrdine;
	private String MailCliente;
	private Date DataOrdine;
	private String stato;
	private String via;
	private String cap;
	private String telefono;
	private String civico;
	
	public OrderBean() {
		TrakingOrdine = "";
		MailCliente = "";
		DataOrdine = new Date(2022-1-1);
		stato = "";
		via = "";
		cap = "";
		telefono = "";
		civico = "";
	}
	
	public int getIdOrdine() {
		return IdOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		IdOrdine = idOrdine;
	}
	public String getTrakingOrdine() {
		return TrakingOrdine;
	}
	public void setTrakingOrdine(String trakingOrdine) {
		TrakingOrdine = trakingOrdine;
	}
	public String getMailCliente() {
		return MailCliente;
	}
	public void setMailCliente(String mailCliente) {
		MailCliente = mailCliente;
	}
	public Date getDataOrdine() {
		return DataOrdine;
	}
	public void setDataOrdine(Date dataOrdine) {
		DataOrdine = dataOrdine;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCivico() {
		return civico;
	}
	public void setCivico(String civico) {
		this.civico = civico;
	}
	
	
	

}
