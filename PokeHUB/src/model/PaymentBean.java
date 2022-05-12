package model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;




public class PaymentBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String NumeroCarta;
	private String MailCliente;
	private String IntestatarioCarta;
	private String CVV;
	private Date Scadenza;
	  
	public PaymentBean() {
		
		NumeroCarta = "";
		MailCliente = "";
		IntestatarioCarta = "";
		CVV = "";
		Scadenza = new Date(0, 0, 1);
		
	}

	public String getNumeroCarta() {
		return NumeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		NumeroCarta = numeroCarta;
	}

	public String getMailCliente() {
		return MailCliente;
	}

	public void setMailCliente(String mailCliente) {
		MailCliente = mailCliente;
	}

	public String getIntestatarioCarta() {
		return IntestatarioCarta;
	}

	public void setIntestatarioCarta(String intestatarioCarta) {
		IntestatarioCarta = intestatarioCarta;
	}

	public String getCVV() {
		return CVV;
	}

	public void setCVV(String cVV) {
		CVV = cVV;
	}

	public Date getScadenza() {
		return Scadenza;
	}

	public void setScadenza(Date date) {
		Scadenza = date;
	}

	@Override
	public String toString() {
		return "PaymentBean [NumeroCarta=" + NumeroCarta + ", MailCliente=" + MailCliente + ", IntestatarioCarta="
				+ IntestatarioCarta + ", CVV=" + CVV + ", Scadenza=" + Scadenza + "]";
	}
	
	
}