package model;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;




public class OrderDAO {

	private static final String TABLE_NAME = "ordine";

	
	public synchronized void doSave(OrderBean ordine) throws SQLException {

		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		//INSERT INTO `db_pokehub`.`ordine` (`mail_cliente`, `traking_ordine`, `data_ordine`, `status_consegna`, `via`, `civico`, `cap`, `telefono`) VALUES ('costy', '0123ABC', '2022-12-1', 'IN CONSEGNA', 'Via Marchesa', '405', '80041', '0818591045');
		String insertSQL = "INSERT INTO " + OrderDAO.TABLE_NAME
				+ " (mail_cliente, traking_ordine, data_ordine, status_consegna, via, civico, cap, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, ordine.getMailCliente());
			preparedStatement.setString(2, ordine.getTrakingOrdine());
			preparedStatement.setDate(3, ordine.getDataOrdine());
			preparedStatement.setString(4, ordine.getStato());
			preparedStatement.setString(5, ordine.getVia());
			preparedStatement.setString(6, ordine.getCivico());
			preparedStatement.setString(7, ordine.getCap());
			preparedStatement.setString(8, ordine.getTelefono());

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

	
	public synchronized Collection<OrderBean> doRetrieveByUser(String mail) throws SQLException, IOException {
		
		//fare quando dobbiamo cercare oggetti precisi sul db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrderBean bean;
		Collection<OrderBean> orders = new LinkedList<OrderBean>();

		String selectSQL = "SELECT * FROM " + OrderDAO.TABLE_NAME + " WHERE mail_cliente = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, mail);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean = new OrderBean();
				
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setMailCliente(rs.getString("mail_cliente"));
				bean.setTrakingOrdine(rs.getString("traking_ordine"));
				bean.setDataOrdine(rs.getDate("data_ordine"));
				bean.setStato(rs.getString("status_consegna"));
				bean.setVia(rs.getString("via"));
				bean.setCivico(rs.getString("civico"));
				bean.setCap(rs.getString("cap"));
				bean.setTelefono(rs.getString("telefono"));
				
				orders.add(bean);
			}
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return orders;
	}
	
	
	public synchronized Collection<OrderBean> doRetrieveAll(String order) throws SQLException, IOException {
		

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrderBean bean;
		Collection<OrderBean> orders = new LinkedList<OrderBean>();

		String selectSQL = "SELECT * FROM " + OrderDAO.TABLE_NAME;
			
			if (order != null && !order.equals(""))
			{
				selectSQL += " ORDER BY " + order;
			}
			
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean = new OrderBean();
				
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setMailCliente(rs.getString("mail_cliente"));
				bean.setTrakingOrdine(rs.getString("traking_ordine"));
				bean.setDataOrdine(rs.getDate("data_ordine"));
				bean.setStato(rs.getString("status_consegna"));
				bean.setVia(rs.getString("via"));
				bean.setCivico(rs.getString("civico"));
				bean.setCap(rs.getString("cap"));
				bean.setTelefono(rs.getString("telefono"));
				
				orders.add(bean);
			}
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return orders;
	}
	
	public synchronized Collection<OrderBean> doRetrieveByDate(Date dataInizio, Date dataFine) throws SQLException, IOException {
		

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrderBean bean;
		Collection<OrderBean> orders = new LinkedList<OrderBean>();
		
		
		String selectSQL = "SELECT * FROM " + OrderDAO.TABLE_NAME+ " WHERE data_ordine BETWEEN '"+ dataInizio +"' AND '"+dataFine+"'";
		System.out.println(selectSQL);
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
	
			while (rs.next()) {
				bean = new OrderBean();
				
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setMailCliente(rs.getString("mail_cliente"));
				bean.setTrakingOrdine(rs.getString("traking_ordine"));
				bean.setDataOrdine(rs.getDate("data_ordine"));
				bean.setStato(rs.getString("status_consegna"));
				bean.setVia(rs.getString("via"));
				bean.setCivico(rs.getString("civico"));
				bean.setCap(rs.getString("cap"));
				bean.setTelefono(rs.getString("telefono"));
				
				orders.add(bean);
			}
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return orders;
	}
	
	
	
	public synchronized OrderBean doRetrieveLastInsert(String mail) throws SQLException, IOException {
		
		//fare quando dobbiamo cercare oggetti precisi sul db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrderBean bean = new OrderBean();

		//SELECT * FROM ordine WHERE mail_cliente = "losco" ORDER BY id_ordine DESC LIMIT 1;
		String selectSQL = "SELECT * FROM " + OrderDAO.TABLE_NAME + " WHERE mail_cliente = ? ORDER BY id_ordine DESC LIMIT 1";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, mail);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setMailCliente(rs.getString("mail_cliente"));
				bean.setTrakingOrdine(rs.getString("traking_ordine"));
				bean.setDataOrdine(rs.getDate("data_ordine"));
				bean.setStato(rs.getString("status_consegna"));
				bean.setVia(rs.getString("via"));
				bean.setCivico(rs.getString("civico"));
				bean.setCap(rs.getString("cap"));
				bean.setTelefono(rs.getString("telefono"));
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
	//select *from getLastRecord ORDER BY id DESC LIMIT 1
	

}