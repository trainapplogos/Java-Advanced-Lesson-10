package ua.lviv.trainapplogos.service;

import java.util.Map;

import ua.lviv.trainapplogos.domain.Product;
import ua.lviv.trainapplogos.shared.AbstractCRUD;

public interface ProductService extends AbstractCRUD<Product> {
	public Map<Integer, Product> readAllMap();
}
