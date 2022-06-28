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




public class RatingDAO {

	private static final String TABLE_NAME = "valutazione";

	
	public synchronized void doSave(RatingBean valutazione) throws SQLException {
		
		//fare quando dobbiamo inserire oggetti nel db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + RatingDAO.TABLE_NAME
				+ " (mail_cliente, identificativo_prodotto, descrizione, punteggio) VALUES (?, ?, ?, ?,)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, valutazione.getMailCliente() );
			preparedStatement.setInt(2, valutazione.getIdProdotto() );
			preparedStatement.setString(3, valutazione.getDescrizione() );
			preparedStatement.setDouble(4, valutazione.getPunteggio() );
			

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

	
	public synchronized RatingBean doRetrieveByKey(String idUtente, int idProdotto) throws SQLException, IOException {
		
		//fare quando dobbiamo cercare oggetti precisi sul db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		RatingBean bean = new RatingBean();

		String selectSQL = "SELECT * FROM " + RatingDAO.TABLE_NAME + " WHERE mail_cliente = ? AND identificativo_prodotto = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, idUtente);
			preparedStatement.setInt(2, idProdotto);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean.setIdProdotto(rs.getInt("identificativo_prodotto"));
				bean.setMailCliente(rs.getString("mail_cliente"));
				bean.setPunteggio(rs.getDouble("punteggio"));
				bean.setDescrizione(rs.getString("descrizione"));	
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
	

}