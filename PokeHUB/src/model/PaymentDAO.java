package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;


public class PaymentDAO {


		private static final String TABLE_NAME = "pagamento";

		
		public synchronized void doSave(PaymentBean pagamento) throws SQLException {
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " +PaymentDAO.TABLE_NAME
					+ " (numero_carta, mail_cliente, intestatario_carta, cvv, scadenza) VALUES (?,?,?,?,?)";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				
				preparedStatement.setString(1, pagamento.getNumeroCarta() );
				preparedStatement.setString(2,pagamento.getMailCliente());
				preparedStatement.setString(3, pagamento.getIntestatarioCarta());
				preparedStatement.setString(4, pagamento.getCVV());
				preparedStatement.setString(5,pagamento.getScadenza()); 

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

		
		public synchronized PaymentBean doRetrieveByKey(String numero_carta) throws SQLException {
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			PaymentBean bean = new PaymentBean();

			String selectSQL = "SELECT * FROM " + PaymentDAO.TABLE_NAME + " WHERE numero_carta = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				preparedStatement.setString(1, numero_carta);
				
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					bean.setNumeroCarta(rs.getString("numero_carta"));
					bean.setMailCliente(rs.getString("mail_cliente"));
					bean.setIntestatarioCarta(rs.getString("intestatario_carta"));
					bean.setCVV(rs.getString("cvv"));
					bean.setScadenza(rs.getString("scadenza"));
					
					
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

		
		public synchronized boolean doDelete(String numero_carta, String intestatario) throws SQLException {
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			int result = 0;

			String deleteSQL = "DELETE FROM " + PaymentDAO.TABLE_NAME + " WHERE numero_carta = ? AND mail_cliente = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				
				
				preparedStatement.setString(1, numero_carta);
				preparedStatement.setString(2, intestatario);
				
				result = preparedStatement.executeUpdate();
				connection.commit();
				System.out.println("Record cancellati: "+result);

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

		
		public synchronized Collection<PaymentBean> doRetrieveAllByUser(String mail, String order) throws SQLException {
			
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<PaymentBean> payments = new LinkedList<PaymentBean>();

			String selectSQL = "SELECT * FROM " + PaymentDAO.TABLE_NAME+" WHERE mail_cliente = ?";

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				preparedStatement.setString(1, mail);
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					PaymentBean bean = new PaymentBean();

					
					bean.setNumeroCarta(rs.getString("numero_carta"));
					bean.setIntestatarioCarta(rs.getString("intestatario_carta"));
					bean.setMailCliente(rs.getString("mail_cliente"));
					bean.setScadenza(rs.getString("scadenza"));
					bean.setCVV(rs.getString("cvv"));
					
					payments.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return payments;
		}
		public void doUpdate(PaymentBean pagamento) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String updateSQL =" UPDATE "+PaymentDAO.TABLE_NAME+" SET numero_carta =?, mail_cliente=?, intestatario_carta=?, cvv=?, scadenza=? WHERE (numero_carta =?)" ;

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
				
				preparedStatement.setString(1, pagamento.getNumeroCarta() );
				preparedStatement.setString(2,pagamento.getMailCliente());
				preparedStatement.setString(3, pagamento.getIntestatarioCarta());
				preparedStatement.setString(4, pagamento.getCVV());
				preparedStatement.setString(5,pagamento.getScadenza()); 


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
