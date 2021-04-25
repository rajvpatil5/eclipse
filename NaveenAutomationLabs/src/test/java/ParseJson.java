import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.qa.base.TestBase;

public class ParseJson {

	public static String getValueByJPath(JSONObject responsejson, String jpath) {
		Object obj = responsejson;
		for (String s : jpath.split("/")) {
			if (!s.isEmpty()) {
				if (!(s.contains("[") || s.contains("]"))) {
					obj = ((JSONObject) obj).get(s);
				} else if (s.contains("[") || s.contains("]")) {
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]))
							.get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
				}
			}
		}
		return obj.toString();
	}

	@Test
	public void JsonParser() {
		try {
			CloseableHttpClient httpClients = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(new TestBase().getURL);

			// pass headers
			HashMap<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");

			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}

			CloseableHttpResponse closeableHttpResponse = httpClients.execute(httpGet);

			// we will get status code
			int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
			System.out.println("Status code -------> " + statusCode);

			// we will get json response
			String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			JSONObject responsejson = new JSONObject(responseString);
			System.out.println("Response JSON is -------> " + responsejson);

			// we will parse json by passing JSON object and desired value
			String valueByJPath = getValueByJPath(responsejson, "/data[0]/last_name");
//			String valueByJPath = getValueByJPath(responsejson,"/per_page");
//			String valueByJPath = getValueByJPath(responsejson,"/total");
//			String valueByJPath = getValueByJPath(responsejson,"/data");
			System.out.println(valueByJPath);

			// we will get response headers
			Header[] headersArray = closeableHttpResponse.getAllHeaders();
			HashMap<String, String> allHeadres = new HashMap<String, String>();
			for (Header header : headersArray) {
				allHeadres.put(header.getName(), header.getValue());
			}
			System.out.println("Response Headers is -------> " + allHeadres);

//			for (Entry<String, String> entry : allHeadres.entrySet()) {
//				System.out.println(entry.getKey() + " : " + entry.getValue());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
