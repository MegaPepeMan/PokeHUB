package model;

import java.io.InputStream;
import java.io.Serializable;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idProdotto;
	private String nomeProdotto;
	private double prezzoVetrina;
	private String descrizione;
	private double iva;
	private boolean prodottoMostrato;
	private String categoriaProdotto;
	private int quantita;
	private String immagineProdotto;
	private InputStream immagineUpload;

	

	public ProductBean() {
		idProdotto = 0;
		nomeProdotto = "";
		prezzoVetrina = 0.0;
		descrizione = "";
		iva = 0.0;
		categoriaProdotto = "";
		quantita = 0;
	}

	public int getIdProdotto() {
		return idProdotto;
	}
	
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	public double getPrezzoVetrina() {
		return prezzoVetrina;
	}

	public void setPrezzoVetrina(double prezzoVetrina) {
		this.prezzoVetrina = prezzoVetrina;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public boolean isProdottoMostrato() {
		return prodottoMostrato;
	}

	public void setProdottoMostrato(boolean prodottoMostrato) {
		this.prodottoMostrato = prodottoMostrato;
	}

	public String getCategoriaProdotto() {
		return categoriaProdotto;
	}

	public void setCategoriaProdotto(String categoriaProdotto) {
		this.categoriaProdotto = categoriaProdotto;
	}
	
	public int getQuantita() {
		return quantita;
	}
	
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getImmagineProdotto() {
		return immagineProdotto;
	}

	public void setImmagineProdotto(String immagineProdotto) {
		this.immagineProdotto = immagineProdotto;
	}
	
	public InputStream getImmagineUpload() {
		return immagineUpload;
	}

	public void setImmagineUpload(InputStream immagineUpload) {
		this.immagineUpload = immagineUpload;
	}
	
	@Override
	public String toString() {
		return "ProductBean [idProdotto=" + idProdotto + ", nomeProdotto=" + nomeProdotto + ", prezzoVetrina="
				+ prezzoVetrina + ", descrizione=" + descrizione + ", iva=" + iva + ", prodottoMostrato="
				+ prodottoMostrato + ", categoriaProdotto=" + categoriaProdotto + ", quantita=" + quantita + "]";
	}
	
	

	
}
