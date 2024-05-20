package dao;

import java.util.List;

import model.Category;

public interface ICategoryDao {
	
	Category findById(Integer categoryId);

	Category findByName(String categoryName);

	Category save(Category category);

	List<Category> findAll();
}
