package ua.lviv.trainapplogos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.trainapplogos.dao.ProductDao;
import ua.lviv.trainapplogos.domain.Product;
import ua.lviv.trainapplogos.utils.ConnectionUtils;

public class ProductDaoImpl implements ProductDao {
	private static String READ_ALL = "SELECT * FROM PRODUCT";
	private static String CREATE = "INSERT INTO PRODUCT (name, description, price) VALUES (?, ?, ?)";
	private static String READ_BY_ID = "SELECT * FROM PRODUCT WHERE ID = ?";
	private static String UPDATE_BY_ID = "UPDATE PRODUCT SET name = ?, description = ?, price = ? WHERE ID = ?";
	private static String DELETE_BY_ID = "DELETE FROM PRODUCT WHERE ID = ?";
	
	private Connection connection;
	private PreparedStatement preparedStatement;

	private static Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);
	
	public ProductDaoImpl() throws ClassNotFoundException, SQLException {
		this.connection = ConnectionUtils.openConnection();
	}
	
	@Override
	public Product create(Product product) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.executeUpdate();
			
			ResultSet resSet =  preparedStatement.getGeneratedKeys();
			resSet.next();
			product.setId(resSet.getInt(1));
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return product;
	}

	@Override
	public Product read(Integer id) {
		Product product = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();

			// Integer productId = result.getInt("id");
			String productName = result.getString("name");
			String description = result.getString("description");
			Double price = result.getDouble("price");
			product = new Product(id, productName, description, price);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return product;
	}

	@Override
	public Product update(Product product) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setInt(4, product.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return product; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}

	@Override
	public void delete(Integer id) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
	
	@Override
	public List<Product> readAll() {
		List<Product> listOfProducts = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Integer productId = result.getInt("id");
				String userName = result.getString("name");
				String description = result.getString("description");
				Double price = result.getDouble("price");		
				
				listOfProducts.add(new Product(productId, userName, description, price));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return listOfProducts;
	}
	 
	 public void closePreparedStatement() {
		 try {
			preparedStatement.close();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
}
