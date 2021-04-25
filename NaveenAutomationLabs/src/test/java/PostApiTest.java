import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.pojo.Users;
import com.qa.pojo.UsersResponse;

public class PostApiTest {

	@Test
	// post method
	public void postTest() {
		try {
			TestBase testBase = new TestBase();

			// pass headers
			HashMap<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");

			// Api to convert pojo class to json object
			ObjectMapper objectMapper = new ObjectMapper();

			// create pojo object and set name and job
			Users users = new Users();
			users.setName("Morpheus");
			users.setJob("Leader");

			// convert pojo object to json string
			String jsonString = objectMapper.writeValueAsString(users);
			System.out.println(jsonString);

			CloseableHttpClient httpClients = HttpClients.createDefault();
			// set url with post method
			HttpPost httpPost = new HttpPost(testBase.getURL);

			// set entity i.e json payload
			httpPost.setEntity(new StringEntity(jsonString));

			// set headers
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}

			CloseableHttpResponse closeableHttpResponse = httpClients.execute(httpPost);

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

			// converting json response to pojo
			UsersResponse readValue = objectMapper.readValue(responseString, UsersResponse.class);
			System.out.println(readValue.getName());
			System.out.println(readValue.getJob());
			System.out.println(readValue.getId());
			System.out.println(readValue.getCreatedAt());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
