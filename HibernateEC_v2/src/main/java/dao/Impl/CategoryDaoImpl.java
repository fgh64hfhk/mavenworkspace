package dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import utils.HibernateUtils;

import dao.ICategoryDao;
import model.Category;

public class CategoryDaoImpl implements ICategoryDao {

	SessionFactory factory;

	public CategoryDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public model.Category findById(Integer categoryId) {
		Session session = factory.getCurrentSession();
		return session.get(Category.class, categoryId);
	}

	@Override
	public model.Category findByName(String categoryName) {
		Session session = factory.getCurrentSession();
		String hql = "FROM model_Category c WHERE c.name = :name";
		Category category = session.createQuery(hql, Category.class).setParameter("name", categoryName)
				.getSingleResult();
		return category;
	}

	@Override
	public model.Category save(model.Category category) {
		Session session = factory.getCurrentSession();
		try {
			session.persist(category);
			return findByName(category.getName());
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	@Override
	public List<model.Category> findAll() {
		Session session = factory.getCurrentSession();
		String hql = "FROM model_Category";
		List<Category> categories = session.createQuery(hql, Category.class).getResultList();
		return categories;
	}

}
