package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

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
	
	
	public synchronized UserBean doRetrieveByUser(String utente) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Creo il bean");
		UserBean bean = new UserBean();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE mail = ?";
		
		System.out.println("Creo la stringa SQL");
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, utente);
			
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
	
	
	public synchronized Collection<UserBean> doRetrieveAll(String user) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Creo il bean");
		

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE mail = ?";
		String selectSQLnoUser = "SELECT * FROM " + TABLE_NAME + " ";
		
		
		System.out.println("Creo la stringa SQL");
		
		Collection<UserBean> utenti = new LinkedList<UserBean>();
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			if(user.equalsIgnoreCase("*")) {
				preparedStatement = connection.prepareStatement(selectSQLnoUser);
			} else {
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, user);
			}
			
			System.out.println("Ho preparato la stringa SQL: "+preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				
				UserBean bean = new UserBean();
				
				bean.setMail(rs.getString("mail"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPassword(rs.getString("password"));
				bean.setCellulare(rs.getString("cellulare"));
				bean.setCategoriaUtente(rs.getString("categoria_utente"));
				
				utenti.add(bean);
				
				System.out.println("Ho aggiunto questo utente alla Collection: "+bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		System.out.println("La collection contiene: "+ utenti);
		return utenti;
	}
	
	
	public synchronized void doUpdateCategory(String utente,String ruolo) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Creo il bean per l'aggiornamento");

		String updateSQL = "UPDATE " + TABLE_NAME + " SET categoria_utente = ? WHERE mail = ?";
		
		System.out.println("Creo la stringa SQL per l'aggiornamento");
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			
			
			preparedStatement.setString(1, ruolo);
			preparedStatement.setString(2, utente);
			
			System.out.println("Ho preparato la stringa SQL: "+preparedStatement);

			preparedStatement.executeUpdate();


			connection.commit();
			
			System.out.println("Ho aggiornato l'utente con successo");
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	
	
	
	
	
	
	
	
	
	public synchronized void doUpdatePhone(String utente,String cellulare) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Creo il bean per l'aggiornamento");

		String updateSQL = "UPDATE " + TABLE_NAME + " SET cellulare = ? WHERE mail = ?";
		
		System.out.println("Creo la stringa SQL per l'aggiornamento");
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			
			
			preparedStatement.setString(1, cellulare);
			preparedStatement.setString(2, utente);
			
			System.out.println("Ho preparato la stringa SQL: "+preparedStatement);

			preparedStatement.executeUpdate();


			connection.commit();
			
			System.out.println("Ho aggiornato l'utente con successo");
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public synchronized void doUpdateName(String utente,String nome) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Creo il bean per l'aggiornamento");

		String updateSQL = "UPDATE " + TABLE_NAME + " SET nome = ? WHERE mail = ?";
		
		System.out.println("Creo la stringa SQL per l'aggiornamento");
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			
			
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, utente);
			
			System.out.println("Ho preparato la stringa SQL: "+preparedStatement);

			preparedStatement.executeUpdate();


			connection.commit();
			
			System.out.println("Ho aggiornato l'utente con successo");
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public synchronized void doUpdateSurname(String utente,String cognome) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Creo il bean per l'aggiornamento");

		String updateSQL = "UPDATE " + TABLE_NAME + " SET cognome = ? WHERE mail = ?";
		
		System.out.println("Creo la stringa SQL per l'aggiornamento");
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			
			
			preparedStatement.setString(1, cognome);
			preparedStatement.setString(2, utente);
			
			System.out.println("Ho preparato la stringa SQL: "+preparedStatement);

			preparedStatement.executeUpdate();


			connection.commit();
			
			System.out.println("Ho aggiornato l'utente con successo");
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
