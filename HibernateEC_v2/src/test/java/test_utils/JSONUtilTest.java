package test_utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.junit.Test;

import utils.JSONUtil;

public class JSONUtilTest {
	
	@Test
	public void testCreateJSONObject() {
		// 創建一個臨時的 JSON 文件
		File tempFile = null;
		try {
			tempFile = File.createTempFile("test", ".json");
			FileWriter fileWriter = new FileWriter(tempFile);
			fileWriter.write("{\"key1\":\"value1\"}");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 確保臨時文件已成功創建
		assertNotNull(tempFile);
		
		// 使用 JSONUtil 來解析文件
		JSONObject jsonObject = JSONUtil.createJSONObject(tempFile);
		
		// 確保 JSON 對象不為空
		assertNotNull(jsonObject);
		
		// 驗證 JSON 對象的內容
		assertEquals("value1", jsonObject.getString("key1"));
		
		// 刪除臨時文件
		if (tempFile != null && tempFile.exists()) {
			tempFile.delete();
		}
	}
}
