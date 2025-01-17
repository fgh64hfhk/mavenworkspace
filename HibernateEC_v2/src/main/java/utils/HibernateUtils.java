package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {

	// 呼叫靜態方法建立 SessionFactory 物件
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("config/hibernate.cfg.xml").build();

			Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
			
			System.out.println("新建SessionFactory成功");
			return sessionFactory;

		} catch (Throwable ex) {
			System.err.println("新建SessionFactory失敗:" + ex.getMessage());
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	// 外界呼叫此靜態方法來取得 SessionFactory物件
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// 外界呼叫此靜態方法來關閉 SessionFactory物件
	public static void close() {
		getSessionFactory().close();
	}

}