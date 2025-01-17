package dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.IProductEntityDao;
import model.ProductEntity;
import utils.HibernateUtils;

public class ProductEntityDaoImpl implements IProductEntityDao {

	SessionFactory factory;

	public ProductEntityDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public ProductEntity findById(Integer productEntityId) {
		Session session = factory.getCurrentSession();
		return session.get(ProductEntity.class, productEntityId);
	}

	@Override
	public ProductEntity save(ProductEntity productEntity) {
		Session session = factory.getCurrentSession();
		session.persist(productEntity);
		
		return findByName(productEntity.getName());
	}

	@Override
	public List<ProductEntity> findAll() {
		Session session = factory.getCurrentSession();
		String hql = "FROM model_Product";
		List<ProductEntity> productEntities = session.createQuery(hql, ProductEntity.class).getResultList();
		return productEntities;
	}

	@Override
	public ProductEntity findByName(String productEntityName) {
		Session session = factory.getCurrentSession();
		String hql = "FROM model_Product p WHERE p.name = :pname";
		ProductEntity product = session.createQuery(hql, ProductEntity.class).setParameter("pname", productEntityName)
				.getSingleResult();
		return product;
	}

}
