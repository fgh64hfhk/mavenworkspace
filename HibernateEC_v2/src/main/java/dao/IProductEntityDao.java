package dao;

import java.util.List;

import model.ProductEntity;

public interface IProductEntityDao {
	
	ProductEntity findById(Integer productEntityId);
	
	ProductEntity findByName(String productEntityName);

	ProductEntity save(ProductEntity productEntity);

	List<ProductEntity> findAll();
}
