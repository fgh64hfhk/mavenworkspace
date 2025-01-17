package service.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.ICategoryDao;
import dao.Impl.CategoryDaoImpl;
import model.Category;
import service.ICategoryService;
import utils.HibernateUtils;

public class CategoryServiceImpl implements ICategoryService {

	ICategoryDao categoryDao;
	SessionFactory factory;

	public CategoryServiceImpl() {
		this.categoryDao = new CategoryDaoImpl();
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Category findById(Integer categoryId) {
		Category category = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			category = categoryDao.findById(categoryId);
			tx.commit();
		} catch (Exception e) {
			System.out.println(e);
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			HibernateUtils.close();
		}
		return category;
	}

	@Override
	public Category findByName(String categoryName) {
		Category category = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			category = categoryDao.findByName(categoryName);
			tx.commit();
		} catch (Exception e) {
			System.out.println(e);
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			HibernateUtils.close();
		}
		return category;
	}

	@Override
	public Category save(Category category) {
		Category category_return = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			category_return = categoryDao.save(category);
			tx.commit();
		} catch (Exception e) {
			System.out.println(e);
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			HibernateUtils.close();
		}
		return category_return;
	}

	@Override
	public List<Category> findAll() {
		List<Category> categories = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			categories = categoryDao.findAll();
			tx.commit();
		} catch (Exception e) {
			System.out.println(e);
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			HibernateUtils.close();
		}
		return categories;
	}
	
	public void close_sessionFactory() {
		this.factory.close();
	}

}
