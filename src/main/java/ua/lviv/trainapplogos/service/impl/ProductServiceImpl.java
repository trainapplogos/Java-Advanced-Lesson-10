package ua.lviv.trainapplogos.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import ua.lviv.trainapplogos.dao.ProductDao;
import ua.lviv.trainapplogos.dao.impl.ProductDaoImpl;
import ua.lviv.trainapplogos.domain.Product;
import ua.lviv.trainapplogos.service.ProductService;

public class ProductServiceImpl implements ProductService {

private static Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);	
//private static ProductServiceImpl productServiceImpl;
private static ProductService productServiceImpl;
private ProductDao productDao;
	
	private ProductServiceImpl() {
		try {
			productDao = new ProductDaoImpl();
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e);
		}
	}
	
	public static ProductService getProductService() {
		if (productServiceImpl == null) {
			productServiceImpl = new ProductServiceImpl();
		}
		return productServiceImpl;
	}

	@Override
	public Product create(Product product) {
		return productDao.create(product);
	}

	@Override
	public Product read(Integer id) {
		return productDao.read(id);
	}

	@Override
	public Product update(Product product) {
		return productDao.update(product);
	}

	@Override
	public void delete(Integer id) {
		productDao.delete(id);
	}

	@Override
	public List<Product> readAll() {
		return productDao.readAll();
	}

	@Override
	public Map<Integer, Product> readAllMap() {
//		List<Product> products = readAll();
//		return products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
		return readAll().stream().collect(Collectors.toMap(Product::getId, Function.identity()));
	}
	
	
}
