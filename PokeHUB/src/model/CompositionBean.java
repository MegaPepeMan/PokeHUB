package model;

import java.io.Serializable;

public class CompositionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int identificativo_prodotto;
	private int identificativo_ordine;
	private int quantita;
	private double prezzo_acquisto;
	private double iva_acquisto;
	
	public CompositionBean() {
		
		identificativo_prodotto=0;
		identificativo_ordine=0;
		quantita=0;
		prezzo_acquisto=0.0;
		iva_acquisto=0.0;
		
	}

	public int getIdentificativo_prodotto() {
		return identificativo_prodotto;
	}

	public void setIdentificativo_prodotto(int identificativo_prodotto) {
		this.identificativo_prodotto = identificativo_prodotto;
	}

	public int getIdentificativo_ordine() {
		return identificativo_ordine;
	}

	public void setIdentificativo_ordine(int identificativo_ordine) {
		this.identificativo_ordine = identificativo_ordine;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getPrezzo_acquisto() {
		return prezzo_acquisto;
	}

	public void setPrezzo_acquisto(double prezzo_acquisto) {
		this.prezzo_acquisto = prezzo_acquisto;
	}

	public double getIva_acquisto() {
		return iva_acquisto;
	}

	public void setIva_acquisto(double iva_acquisto) {
		this.iva_acquisto = iva_acquisto;
	}

	@Override
	public String toString() {
		return "CompositionBean [identificativo_prodotto=" + identificativo_prodotto + ", identificativo_ordine="
				+ identificativo_ordine + ", quantita=" + quantita + ", prezzo_acquisto=" + prezzo_acquisto
				+ ", iva_acquisto=" + iva_acquisto + "]";
	}


}