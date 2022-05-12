package model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Cart {
	
	private ProductDAO product = new ProductDAO();
	
	private Map<Integer,Integer> cartQuantity;
	private Map<Integer,ProductBean> cartProduct;
	
	public Cart() {
		this.cartQuantity = new HashMap<Integer,Integer>();
		this.cartProduct = new HashMap<Integer,ProductBean>();
	}
	
	public boolean isEmpty() {
		if(this.cartProduct.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void remove(Integer key) {
		this.cartQuantity.remove(key);
		this.cartProduct.remove(key);
	}
	
	public ProductBean findObject(Integer key) {
		return this.cartProduct.get(key);
	}
	
	public Integer quantityObject(Integer key) {
		return this.cartQuantity.get(key);
	}
	
	
	//Metodi private
	
	private void insert(Integer key, Integer value) throws SQLException, IOException {
		this.cartQuantity.put(key, value);
		this.cartProduct.put(key, product.doRetrieveByKey(key));
	}
	
	private void newQuantity(Integer key, Integer quantity) throws SQLException, IOException {
		insert(key, quantityObject(key) + quantity);
	}
	
	
	
	
	
	
	
	public Iterator<Integer> iterator() {
		return (this.cartProduct.keySet()).iterator();
	}
	
	public void addProduct(Integer key, Integer quantity) throws SQLException, IOException {
		System.out.println("L'ID �: "+key+" e la quantit� � "+quantity);
		if(isEmpty()) {
			insert(key, quantity);
		} else {
			Integer qtyTemp = quantityObject(key);
			if(qtyTemp == null) {
				insert(key, quantity);
			} else {
				newQuantity(key, quantity);
			}
				
		}
	}
	
	public void setQuantity(int id , int value) {
		this.cartQuantity.put(id, value);
	}
	
	public int getSizeCart() {
		return this.cartProduct.size();
	}

	@Override
	public String toString() {
		return "Cart: "+this.cartQuantity.toString();
	}
	
	
	

}
