package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public class JSONUtil {

	public static JSONObject createJSONObject(File file) {
		JSONObject object = null;
		try {
			// 讀取文件內容為字串
			String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
			// 將字串解析為 JSON 物件
			object = new JSONObject(content);
		} catch (IOException e) {
			e.printStackTrace(); // 處理 IO 異常
		} catch (org.json.JSONException e) {
			e.printStackTrace(); // 處理 JSON 解析異常
		}
		return object; // 返回 JSON 物件
	}
}
