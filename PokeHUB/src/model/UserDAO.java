package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	
	private static final String TABLE_NAME = "utente";
	
public synchronized void doSave(UserBean utente) throws SQLException {

		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UserDAO.TABLE_NAME+ " ( mail, cellulare, password, nome , cognome , categoria_utente) VALUES (?,?,?,?,?,'Cliente')";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, utente.getMail());
			preparedStatement.setString(2,utente.getCellulare());
			preparedStatement.setString(3, utente.getPassword());
			preparedStatement.setString(4,utente.getNome());
			preparedStatement.setString(5,utente.getCognome());
			

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

	
	public synchronized UserBean doRetrieveByKey(String utente,String password) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Creo il bean");
		UserBean bean = new UserBean();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE mail = ? AND password = ?";
		
		System.out.println("Creo la stringa SQL");
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, utente);
			preparedStatement.setString(2, password);
			
			System.out.println("Ho preparato la stringa SQL: "+preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				System.out.println("Mail: " + rs.getString("mail"));
				System.out.println("Nome: " + rs.getString("nome"));
				System.out.println("Cognome: " + rs.getString("cognome"));
				System.out.println("Password: " + rs.getString("password"));
				System.out.println("Cellulare: " + rs.getString("cellulare"));
				System.out.println("Categoria Utente: " + rs.getString("categoria_utente"));
				
				
				
				bean.setMail(rs.getString("mail"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPassword(rs.getString("password"));
				bean.setCellulare(rs.getString("cellulare"));
				bean.setCategoriaUtente(rs.getString("categoria_utente"));
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
