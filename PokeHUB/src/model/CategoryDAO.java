package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;


public class CategoryDAO {


		private static final String TABLE_NAME = "categoria";

		
		public synchronized void doSave(CategoryBean categoria) throws SQLException {
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " +CategoryDAO.TABLE_NAME+ " nome_categoria VALUES ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				
				preparedStatement.setString(1, categoria.getNomeCategoria());
				

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

		
		public synchronized CategoryBean doRetrieveByKey(String nome_categoria) throws SQLException {
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			CategoryBean bean = new CategoryBean();

			String selectSQL = "SELECT * FROM " + CategoryDAO.TABLE_NAME + " WHERE nome_categoria = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setString(1, nome_categoria);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setNomeCategoria(rs.getString("nome_categoria"));
					
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

		
		public synchronized boolean doDelete(String nome_categoria) throws SQLException {
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			int result = 0;

			String deleteSQL = "DELETE FROM " + CategoryDAO.TABLE_NAME + " WHERE nome_categoria = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				
				
				preparedStatement.setString(1, nome_categoria);

				result = preparedStatement.executeUpdate();

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

		
		public synchronized Collection<CategoryBean> doRetrieveAll(String order) throws SQLException {
			
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<CategoryBean> categories = new LinkedList<CategoryBean>();

			String selectSQL = "SELECT * FROM " + CategoryDAO.TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					CategoryBean bean = new CategoryBean();
					bean.setNomeCategoria(rs.getString("nome_categoria"));
					categories.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return categories;
		}
		
		

	}

