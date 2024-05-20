package test_utils;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import utils.JSONUtil;

public class JSONUtilTest2 {
	
	@Test
	public void testCreateJSONObject() {

		System.out.println(new File(".").getAbsolutePath());

		File file = new File("src\\main\\resources\\init\\安全帽.json");
		
		// 使用 JSONUtil 來解析文件
		JSONObject jsonObject = JSONUtil.createJSONObject(file);
		JSONArray array = jsonObject.getJSONArray("安全帽");
		for (Object object : array) {
			JSONObject object2 = (JSONObject) object;
			System.out.println(object2.getString("型號"));
		}

	}
}
