package service.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.ICategoryDao;
import dao.IProductEntityDao;
import dao.Impl.CategoryDaoImpl;
import dao.Impl.ProductEntityDaoImpl;
import model.Category;
import model.ProductEntity;
import service.IProductEntityService;
import utils.HibernateUtils;

public class ProductEntityServiceImpl implements IProductEntityService {

	ICategoryDao categoryDao;
	IProductEntityDao productEntityDao;
	SessionFactory factory;

	public ProductEntityServiceImpl() {
		this.categoryDao = new CategoryDaoImpl();
		this.productEntityDao = new ProductEntityDaoImpl();
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public ProductEntity findById(Integer productEntityId) {
		ProductEntity productEntity = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			productEntity = productEntityDao.findById(productEntityId);
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
		return productEntity;
	}

	@Override
	public ProductEntity findByName(String productEntityName) {
		ProductEntity productEntity = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			productEntity = productEntityDao.findByName(productEntityName);
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
		return productEntity;
	}

	@Override
	public ProductEntity save(ProductEntity productEntity) {
		ProductEntity productEntity_return = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Category category = categoryDao.findByName(productEntity.getCategory().getName());
			if (category != null) {
				System.out.println("Already exist category: " + category);
			} else {
				System.out.println("Insert into category: " + category);
				categoryDao.save(category);
			}
			productEntity_return = productEntityDao.save(productEntity);
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
		return productEntity_return;
	}

	@Override
	public List<ProductEntity> findAll() {
		List<ProductEntity> productEntities = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			productEntities = productEntityDao.findAll();
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
		return productEntities;
	}
	
	public void close_sessionFactory() {
		this.factory.close();
	}

}
