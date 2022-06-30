package model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedList;




public class ProductDAO {

	private static final String TABLE_NAME = "prodotto";

	
	public synchronized void doSave(ProductBean prodotto) throws SQLException {
		
		//fare quando dobbiamo inserire oggetti nel db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductDAO.TABLE_NAME
				+ " (nome_prodotto, prezzo_vetrina, descrizione, iva, prodotto_mostrato, categoria_prodotto, quantita, immagine_prodotto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, prodotto.getNomeProdotto());
			preparedStatement.setDouble(2, prodotto.getPrezzoVetrina());
			preparedStatement.setString(3, prodotto.getDescrizione());
			preparedStatement.setDouble(4, prodotto.getIva());
			preparedStatement.setBoolean(5, prodotto.isProdottoMostrato());
			preparedStatement.setString(6, prodotto.getCategoriaProdotto());
			preparedStatement.setInt(7, prodotto.getQuantita());
			preparedStatement.setBlob(8, prodotto.getImmagineUpload());

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	
	public synchronized ProductBean doRetrieveByKey(int idProdotto) throws SQLException, IOException {
		
		//fare quando dobbiamo cercare oggetti precisi sul db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE id_prodotto = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idProdotto);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean.setIdProdotto(rs.getInt("id_prodotto"));
				bean.setNomeProdotto(rs.getString("nome_prodotto"));
				bean.setPrezzoVetrina(rs.getDouble("prezzo_vetrina"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setIva(rs.getDouble("iva"));
				bean.setProdottoMostrato(rs.getBoolean("prodotto_mostrato"));
				bean.setCategoriaProdotto(rs.getString("categoria_prodotto"));
				bean.setQuantita(rs.getInt("quantita"));
				
				if( rs.getBlob("immagine_prodotto")!= null ) {
					Blob blob = rs.getBlob("immagine_prodotto");

					InputStream inputStream = blob.getBinaryStream();
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
					int bytesRead = -1;

					while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
					}

					byte[] imageBytes = outputStream.toByteArray();
					String base64Image = Base64.getEncoder().encodeToString(imageBytes);

					inputStream.close();
					outputStream.close();
					
					bean.setImmagineProdotto(base64Image);
					
				}
				
				
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}
	
public synchronized Collection<ProductBean> doRetrieveSuggest(String StringaParziale) throws SQLException, IOException {
		
		//fare quando dobbiamo cercare oggetti precisi sul db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE nome_prodotto LIKE ?";
		Collection<ProductBean> products = new LinkedList<ProductBean>();
			
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			String stringaRicerca = StringaParziale.concat("%");
			preparedStatement.setString(1, stringaRicerca);
			
			System.out.println(stringaRicerca+ " nella stringa: " + selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			
			
			
			while (rs.next()) {
				bean = new ProductBean();
				bean.setIdProdotto(rs.getInt("id_prodotto"));
				bean.setNomeProdotto(rs.getString("nome_prodotto"));
				bean.setProdottoMostrato(rs.getBoolean("prodotto_mostrato"));
				System.out.println("Gli oggetti trovati sono: "+bean.getIdProdotto()+ " " + bean.getNomeProdotto() + " " + bean.isProdottoMostrato() );
				if( bean.isProdottoMostrato() ) {
					products.add(bean);
				}

			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		products.toString();
		return products;
	}

	
	
	public synchronized boolean doDelete(int idProdotto) throws SQLException {
		
		//fare quando dobbiamo cancellare oggetti precisi sul db
		System.out.println("Procediamo alla delete");
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductDAO.TABLE_NAME + " WHERE id_prodotto = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			
			preparedStatement.setInt(1, idProdotto);
			System.out.println(deleteSQL);
			
			result = preparedStatement.executeUpdate();
			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException, IOException {
		
		//fare quando dobbiamo cercare tutti gli oggetti di una tabella
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setIdProdotto(rs.getInt("id_prodotto"));
				bean.setNomeProdotto(rs.getString("nome_prodotto"));
				bean.setPrezzoVetrina(rs.getDouble("prezzo_vetrina"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setIva(rs.getDouble("iva"));
				bean.setProdottoMostrato(rs.getBoolean("prodotto_mostrato"));
				bean.setCategoriaProdotto(rs.getString("categoria_prodotto"));
				bean.setQuantita(rs.getInt("quantita"));
				
				if( rs.getBlob("immagine_prodotto")!= null ) {
					Blob blob = rs.getBlob("immagine_prodotto");

					InputStream inputStream = blob.getBinaryStream();
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
					int bytesRead = -1;

					while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
					}

					byte[] imageBytes = outputStream.toByteArray();
					String base64Image = Base64.getEncoder().encodeToString(imageBytes);

					inputStream.close();
					outputStream.close();
					
					bean.setImmagineProdotto(base64Image);
					
				}
				
				
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	
	public void doUpdate(ProductBean prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL =" UPDATE "+ProductDAO.TABLE_NAME+" SET nome_prodotto = ?, prezzo_vetrina = ?, descrizione = ?, iva = ?, prodotto_mostrato = ?, categoria_prodotto = ?, quantita = ?, immagine_prodotto = ? WHERE (id_prodotto = ?)" ;
		//UPDATE `db_pokehub`.`prodotto` SET `nome_prodotto` = '1234', `prezzo_vetrina` = '124.00', `descrizione` = '123quattroCinque', `iva` = '19.00', `prodotto_mostrato` = '0', `categoria_prodotto` = 'Accessori', `quantita` = '12', `immagine_prodotto` = ? WHERE (`id_prodotto` = '17');
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			
			preparedStatement.setString(1, prodotto.getNomeProdotto() );
			preparedStatement.setDouble(2, prodotto.getPrezzoVetrina() );
			preparedStatement.setString(3, prodotto.getDescrizione() );
			preparedStatement.setDouble(4, prodotto.getIva() );
			preparedStatement.setBoolean(5, prodotto.isProdottoMostrato() );
			preparedStatement.setString(6, prodotto.getCategoriaProdotto() );
			preparedStatement.setInt(7, prodotto.getQuantita() );
			preparedStatement.setBlob(8, prodotto.getImmagineUpload());
			preparedStatement.setInt(9, prodotto.getIdProdotto() );
			
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public void doUpdateNoImage(ProductBean prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL =" UPDATE "+ProductDAO.TABLE_NAME+" SET nome_prodotto = ?, prezzo_vetrina = ?, descrizione = ?, iva = ?, prodotto_mostrato = ?, categoria_prodotto = ?, quantita = ? WHERE (id_prodotto = ?)" ;
		//UPDATE `db_pokehub`.`prodotto` SET `nome_prodotto` = '1234', `prezzo_vetrina` = '124.00', `descrizione` = '123quattroCinque', `iva` = '19.00', `prodotto_mostrato` = '0', `categoria_prodotto` = 'Accessori', `quantita` = '12', `immagine_prodotto` = ? WHERE (`id_prodotto` = '17');
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			
			preparedStatement.setString(1, prodotto.getNomeProdotto() );
			preparedStatement.setDouble(2, prodotto.getPrezzoVetrina() );
			preparedStatement.setString(3, prodotto.getDescrizione() );
			preparedStatement.setDouble(4, prodotto.getIva() );
			preparedStatement.setBoolean(5, prodotto.isProdottoMostrato() );
			preparedStatement.setString(6, prodotto.getCategoriaProdotto() );
			preparedStatement.setInt(7, prodotto.getQuantita() );
			preparedStatement.setInt(8, prodotto.getIdProdotto() );
			
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	

}