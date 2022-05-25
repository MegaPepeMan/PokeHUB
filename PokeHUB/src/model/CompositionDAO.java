package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;


public class CompositionDAO {


		private static final String TABLE_NAME = "composizione";

		
		public synchronized void doSave(CompositionBean composizione) throws SQLException {
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " +CompositionDAO.TABLE_NAME
					+ " ( identificativo_prodotto,identificativo_ordine,quantita ,prezzo_acquisto,iva_acquisto) VALUES (?,?,?,?,?)";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				
				preparedStatement.setInt(1, composizione.getIdentificativo_prodotto());
				preparedStatement.setInt(2,composizione.getIdentificativo_ordine());
				preparedStatement.setInt(3, composizione.getQuantita());
				preparedStatement.setDouble(4, composizione.getPrezzo_acquisto());
				preparedStatement.setDouble(5, composizione.getIva_acquisto());
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

		
		public synchronized CompositionBean doRetrieveByKey(int identificativo_prodotto,int identificativo_ordine) throws SQLException {
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			CompositionBean bean = new CompositionBean();

			String selectSQL = "SELECT * FROM " + CompositionDAO.TABLE_NAME + " WHERE identificativo_prodotto = ? AND identificativo_ordine=?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				preparedStatement.setInt(1, identificativo_prodotto);
				preparedStatement.setInt(2, identificativo_ordine);
				
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					bean.setIdentificativo_ordine(rs.getInt("identificativo_ordine"));
					bean.setIdentificativo_prodotto(rs.getInt("identificativo_prodotto"));
					bean.setQuantita(rs.getInt("quantita"));
					bean.setPrezzo_acquisto(rs.getDouble("prezzo_acquisto"));
					bean.setIva_acquisto(rs.getDouble("iva_acquisto"));
					
					
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

		
		public synchronized boolean doDelete(int identificativo_prodotto,int identificativo_ordine) throws SQLException {
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			int result = 0;

			String deleteSQL = "DELETE FROM " + CompositionDAO.TABLE_NAME + " WHERE identificativo_prodotto = ? AND identificativo_ordine = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				
				
				preparedStatement.setInt(1, identificativo_prodotto);
				preparedStatement.setInt(2, identificativo_ordine);

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

		
		public synchronized Collection<CompositionBean> doRetrieveAll(String order) throws SQLException {
			
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<CompositionBean> compositions = new LinkedList <CompositionBean>();

			String selectSQL = "SELECT * FROM " + CompositionDAO.TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					CompositionBean bean = new CompositionBean();

					
					bean.setIdentificativo_ordine(rs.getInt("identificativo_ordine"));
					bean.setIdentificativo_prodotto(rs.getInt("identificativo_prodotto"));
					
					compositions.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return compositions;
		}
		
		
		public void doUpdate(CompositionBean composizione) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String updateSQL =" UPDATE "+CompositionDAO.TABLE_NAME+" SET quantita = ?,prezzo_acquisto = ?,iva_acquisto = ? WHERE (identificativo_ordine = ? AND identificativo_prodotto= ?)" ;

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
				
				preparedStatement.setInt(1, composizione.getQuantita());
				preparedStatement.setDouble(2, composizione.getPrezzo_acquisto());
				preparedStatement.setDouble(3, composizione.getIva_acquisto());
				preparedStatement.setInt(4,composizione.getIdentificativo_ordine());
				preparedStatement.setInt(5, composizione.getIdentificativo_prodotto());
				
				

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
		
		
		public synchronized Collection<CompositionBean> doRetrieveByOrder(int id, String order) throws SQLException {

			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<CompositionBean> compositions = new LinkedList<CompositionBean>();

			String selectSQL = "SELECT * FROM " + CompositionDAO.TABLE_NAME+" WHERE identificativo_ordine = ?";

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				preparedStatement.setInt(1, id);
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					CompositionBean bean = new CompositionBean();

					
					bean.setIdentificativo_prodotto(rs.getInt("identificativo_prodotto"));
					bean.setIdentificativo_ordine(rs.getInt("identificativo_ordine"));
					bean.setQuantita(rs.getInt("quantita"));
					bean.setPrezzo_acquisto(rs.getDouble("prezzo_acquisto"));
					bean.setIva_acquisto(rs.getDouble("iva_acquisto"));
					
					
					System.out.println("Id Ordine: "+bean.getIdentificativo_ordine());
					System.out.println("Id Prodotto: "+bean.getIdentificativo_prodotto());
					System.out.println("IVA: "+bean.getIva_acquisto());
					System.out.println("Prezzo senza IVA: "+bean.getPrezzo_acquisto());
					System.out.println("Quantita' : "+bean.getQuantita());
					
					compositions.add(bean);
					
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return compositions;
		}


	}

