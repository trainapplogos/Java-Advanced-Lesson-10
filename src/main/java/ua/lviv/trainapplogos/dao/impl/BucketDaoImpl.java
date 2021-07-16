package ua.lviv.trainapplogos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.trainapplogos.dao.BucketDao;
import ua.lviv.trainapplogos.domain.Bucket;
import ua.lviv.trainapplogos.domain.UserAccount;
import ua.lviv.trainapplogos.service.BucketService;
import ua.lviv.trainapplogos.service.UserAccountService;
import ua.lviv.trainapplogos.service.impl.BucketServiceImpl;
import ua.lviv.trainapplogos.service.impl.UserAccountServiceImpl;
import ua.lviv.trainapplogos.utils.ConnectionUtils;

public class BucketDaoImpl implements BucketDao {
	private static String READ_ALL = "SELECT * FROM BUCKET";
	private static String CREATE = "INSERT INTO BUCKET (user_id, product_id, purchase_date) VALUES (?, ?, ?)";
	private static String READ_BY_ID = "SELECT * FROM BUCKET WHERE ID = ?";
	private static String DELETE_BY_ID = "DELETE FROM BUCKET WHERE ID = ?";

	private static Logger LOGGER = Logger.getLogger(BucketDaoImpl.class);
	
	private Connection connection;
	private PreparedStatement preparedStatement;

	public BucketDaoImpl() throws ClassNotFoundException, SQLException {
		this.connection = ConnectionUtils.openConnection();
	}
	
	@Override
	public Bucket create(Bucket bucket) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, bucket.getUserId());
			preparedStatement.setInt(2, bucket.getProductId());
			preparedStatement.setDate(3, new java.sql.Date(bucket.getPurchaseDate().getTime()));
			preparedStatement.executeUpdate();
			
			ResultSet resSet =  preparedStatement.getGeneratedKeys();
			resSet.next();
			bucket.setId(resSet.getInt(1));
		} catch (SQLException e) {
			LOGGER.error(e);
			//e.printStackTrace();
		}

		return bucket;
	}
	
	@Override
	public Bucket read(Integer id) {
		Bucket bucket = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();

			// Integer bucketId = result.getInt("id");
			Integer userId = result.getInt("user_id");
			Integer productId = result.getInt("product_id");
			Date purchaseDate = result.getDate("purchase_date");
			bucket = new Bucket(id, userId, productId, purchaseDate);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return bucket;
	}



	@Override
	public Bucket update(Bucket t) {
		throw new IllegalStateException("there is no update for bucket!");
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
	public List<Bucket> readAll() {
		List<Bucket> BucketRecords = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Integer bucketId = result.getInt("id");
				Integer userId = result.getInt("user_id");
				Integer productId = result.getInt("product_id");
				Date purchaseDate = result.getDate("purchase_date");
				BucketRecords.add(new Bucket(bucketId, userId, productId, purchaseDate));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return BucketRecords;
	}
	
	public void closePreparedStatement() {
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
}
