import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;

public class GetApiTest extends TestBase {

	TestBase testbase;
	String serviceURL;
	String url;
	String getURL;

	@BeforeMethod
	public void setup() {
		testbase = new TestBase();
		url = prop.getProperty("URL");
		serviceURL = prop.getProperty("serviceURL");
		getURL = url + serviceURL;
	}

	@Test
	// get method without passing headers
	public void getTest() {
		try {
			CloseableHttpClient httpClients = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(getURL);
			CloseableHttpResponse closeableHttpResponse = httpClients.execute(httpGet);

			// we will get status code
			int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
			System.out.println("Status code -------> " + statusCode);

			// we will get json response
			String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			JSONObject responsejson = new JSONObject(responseString);
			System.out.println("Response JSON is -------> " + responsejson);

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
	
	@Test
	// get method with headers
	public void getTestWithHeaders() {
		try {
			CloseableHttpClient httpClients = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(getURL);
			
			//pass headers
			HashMap<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");
			
			for(Map.Entry<String, String> entry : headerMap.entrySet()) {
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

























