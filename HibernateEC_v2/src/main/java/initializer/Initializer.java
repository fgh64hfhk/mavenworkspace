package initializer;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

import model.Category;
import model.ProductEntity;
import service.ICategoryService;
import service.IProductEntityService;
import service.Impl.CategoryServiceImpl;
import service.Impl.ProductEntityServiceImpl;
import utils.JSONUtil;

public class Initializer {
	private static ICategoryService categoryService;
	private static IProductEntityService productEntityService;

	public Initializer() {
		categoryService = new CategoryServiceImpl();
		productEntityService = new ProductEntityServiceImpl();
	}

	public void initialize_product(Session session) {
		
//		System.out.println(new File(".").getAbsolutePath());
		
		File file = new File("C:\\Users\\user\\eclipse-mavenworkspace\\HibernateEC_v2\\src\\main\\resources\\init\\安全帽.json");
		JSONObject jsonObject = JSONUtil.createJSONObject(file);
		
		String categoryName = "安全帽";
		Category category = new Category(categoryName, "包含全罩式、3/4罩式、復古式、可掀式");
		Transaction transaction = session.beginTransaction();
		session.save(category);
		transaction.commit();

		JSONArray array = jsonObject.getJSONArray(categoryName);
		
		for (int i = 0; i < array.length(); i++) {
			Transaction trx = session.beginTransaction();

			ProductEntity product = new ProductEntity();
			String[] strs = array.getJSONObject(i).getString("型號").split(" ");
			product.setModel(strs[0].trim());
			product.setSubCategory(array.getJSONObject(i).getString("種類"));
			product.setBrand(array.getJSONObject(i).getString("品牌"));
			product.setName(array.getJSONObject(i).getString("型號"));
			product.setDescription(array.getJSONObject(i).getString("介紹"));
			product.setPrice(array.getJSONObject(i).getDouble("價格"));
			product.setCategory(category);

			session.save(product);
			
			trx.commit();
		}

		((CategoryServiceImpl) categoryService).close_sessionFactory();
		((ProductEntityServiceImpl) productEntityService).close_sessionFactory();
	}
	
}
