package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;


public class AddressDAO {


		private static final String TABLE_NAME = "indirizzo";

		
		public synchronized void doSave(AddressBean indirizzo) throws SQLException {
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			//INSERT INTO `db_pokehub`.`indirizzo` (`mail_cliente`, `via`, `civico`, `telefono`, `cap`) VALUES ('costy', 'Via Incredibile', '456', '21383271', '8456');

			String insertSQL = "INSERT INTO " +AddressDAO.TABLE_NAME
					+ " ( mail_cliente, via, civico, telefono , cap) VALUES (?,?,?,?,?)";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				
				preparedStatement.setString(1, indirizzo.getMailCliente());
				preparedStatement.setString(2, indirizzo.getVia());
				preparedStatement.setString(3, indirizzo.getCivico());
				preparedStatement.setString(4, indirizzo.getTelefono());
				preparedStatement.setString(5, indirizzo.getCap());

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

		
		public synchronized AddressBean doRetrieveByKey(String mail_cliente, String id_indirizzo) throws SQLException {
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			AddressBean bean = new AddressBean();

			String selectSQL = "SELECT * FROM " + AddressDAO.TABLE_NAME + " WHERE mail_cliente = ? AND id_indirizzo = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				preparedStatement.setString(1, mail_cliente);
				preparedStatement.setString(2, id_indirizzo);
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setIdIndirizzo(rs.getInt("id_indirizzo"));
					bean.setMailCliente(rs.getString("mail_cliente"));
					bean.setVia(rs.getString("via"));
					bean.setCivico(rs.getString("civico"));
					bean.setTelefono(rs.getString("telefono"));
					bean.setCap(rs.getString("cap"));
					
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

		
		public synchronized boolean doDelete(String mail_utente, int idIndirizzo) throws SQLException {
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			int result = 0;

			//DELETE FROM `db_pokehub`.`indirizzo` WHERE (`id_indirizzo` = '5') and (`mail_cliente` = 'costy');
			String deleteSQL = "DELETE FROM " + AddressDAO.TABLE_NAME + " WHERE id_indirizzo = ? AND mail_cliente = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				
				
				preparedStatement.setInt(1, idIndirizzo);
				preparedStatement.setString(2, mail_utente);

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

		
		public synchronized Collection<AddressBean> doRetrieveAllByUser(String mail, String order) throws SQLException {
			
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<AddressBean> address = new LinkedList<AddressBean>();

			String selectSQL = "SELECT * FROM " + AddressDAO.TABLE_NAME+" WHERE mail_cliente = ?";
			
			
			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				preparedStatement.setString(1, mail);
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					AddressBean bean = new AddressBean();

					bean.setMailCliente(rs.getString("mail_cliente"));
					bean.setCap(rs.getString("cap"));
					bean.setCivico(rs.getString("civico"));
					bean.setIdIndirizzo(rs.getInt("id_indirizzo"));
					bean.setTelefono(rs.getString("telefono"));
					bean.setVia(rs.getString("via"));
					
					address.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return address;
		}
		
		public void doUpdate(AddressBean indirizzo) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String updateSQL =" UPDATE "+AddressDAO.TABLE_NAME+" SET via = ?, civico=?, telefono=? , cap = ? WHERE (mail_cliente = ?) AND (id_indirizzo = ?)" ;
			//UPDATE "+ProductDAO.TABLE_NAME+" SET nome_prodotto = ?, prezzo_vetrina = ?, descrizione = ?, iva = ?, prodotto_mostrato = ?, categoria_prodotto = ?, quantita = ? WHERE (id_prodotto = ?)"
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, indirizzo.getVia());
				preparedStatement.setString(2, indirizzo.getCivico());
				preparedStatement.setString(3, indirizzo.getTelefono());
				preparedStatement.setString(4, indirizzo.getCap());
				preparedStatement.setString(5, indirizzo.getMailCliente());
				preparedStatement.setInt(6, indirizzo.getIdIndirizzo());
				

				
				

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
