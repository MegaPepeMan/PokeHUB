package model;

import java.io.Serializable;

public class RatingBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String mailCliente;
	private Integer idProdotto;
	private String descrizione;
	private double punteggio;
	
	
	
	public RatingBean() {
		super();
		this.mailCliente = "";
		this.idProdotto = null;
		this.descrizione = "";
		this.punteggio = 0;
	}

	public String getMailCliente() {
		return mailCliente;
	}
	
	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}
	
	public Integer getIdProdotto() {
		return idProdotto;
	}
	
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public double getPunteggio() {
		return punteggio;
	}
	
	public void setPunteggio(double punteggio) {
		this.punteggio = punteggio;
	}

	@Override
	public String toString() {
		return "RatingBean [mailCliente=" + mailCliente + ", idProdotto=" + idProdotto + ", descrizione=" + descrizione
				+ ", punteggio=" + punteggio + "]";
	}
	
	
}
