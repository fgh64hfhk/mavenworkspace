package listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import initializer.Initializer;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import utils.HibernateUtils;

@WebListener
public class WebAppContextListener implements ServletContextListener {
	
	private SessionFactory factory;
	private Session session;

	public void contextInitialized(ServletContextEvent sce) {
		
		System.out.println("================ WebAppContextListener =================");
		
    	System.out.println("================ 1. start create factory =================");
    	factory = HibernateUtils.getSessionFactory();
    	session = factory.openSession();
    	System.out.println("================ 2. finish create factory =================");

    	System.out.println("================ 3. start Initializer =================");
    	Initializer initializer = new Initializer();
    	initializer.initialize_product(session);
    	System.out.println("================ 4. finish Initializer =================");
    	
		System.out.println("================ WebAppContextListener End =================");

	}

	@Override
    public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("================ 3 start close factory =================");
    	factory.close();
    	System.out.println("================ 4. finish close factory =================");

    	Enumeration<Driver> drivers = DriverManager.getDrivers();
    	Driver driver = null;
    	while (drivers.hasMoreElements()) {
    		try {
				driver = drivers.nextElement();
				DriverManager.deregisterDriver(driver);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
    	AbandonedConnectionCleanupThread.checkedShutdown();
    }

}
