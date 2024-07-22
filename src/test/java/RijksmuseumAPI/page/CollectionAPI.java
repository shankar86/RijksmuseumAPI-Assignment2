package RijksmuseumAPI.page;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CollectionAPI {

	static String BASEURL = "https://www.rijksmuseum.nl";
	private static String resourceUrl;
	public static HashMap<String, String> pathParamMap = new HashMap<String, String>();
	public static HashMap<String, String> headerMap = new HashMap<String, String>();
	public static Response response;
	public static String responseBody;
	static Logger log = Logger.getLogger(CollectionAPI.class.getName());

	public static void getResourceUrl(DataTable arg1) {

		java.util.List<java.util.List<String>> dataTable = arg1.cells();

		resourceUrl = dataTable.get(0).get(1);
		System.out.println("Resource Url is:" + resourceUrl);
	}

	public static Map<String, String> getRequestHeaders(DataTable arg1) {
		headerMap.clear();

		java.util.List<java.util.List<String>> dataTable = arg1.cells();

		for (int i = 0; i < dataTable.size(); i++) {

			String key = dataTable.get(i).get(0).toString();
			String value = dataTable.get(i).get(1).toString();
			headerMap.put(key, value);
		}
		return headerMap;

	}

	public static Response collectionAPICall() {

		RestAssured.baseURI = BASEURL;
		RequestSpecification request = RestAssured.given();
		request.headers(headerMap);

		response = request.when().get(resourceUrl);

		log.info("Response is-->" + response.asString());

		responseBody = response.asString();

		return response;

	}

	public static void verifyResponseCode(int responseCode) {

		Assert.assertEquals(responseCode, response.getStatusCode());

	}

	public static void verifyResponseBody(String responseBodyValue) {

		Assert.assertTrue(responseBodyValue + " Value is Not Available in Collection API Response Body",
				responseBody.contains(responseBodyValue));
	}

	public static void verifyResponseBodyDetails(String string, String string2) {

		ValidatableResponse responseBodyDetails = response.then();
		List<Object> artObjects = responseBodyDetails.extract().body().jsonPath().getList("artObjects");
		int objectSize = artObjects.size();
		JSONArray jsonArray = new JSONArray(artObjects);
		for (int i = 0; i < objectSize; i++) {
			Assert.assertTrue(artObjects + " Value is Not Available in Collection API Response Body",
					artObjects.get(i).toString().contains("objectNumber"));
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			//String objectNumber = jsonObject.getString("objectNumber");
			//System.out.println("objectNumber: " + objectNumber);
			if (jsonObject.getString("objectNumber").toString().equals(string2)) {
				responseBodyDetails.assertThat().body("artObjects[" + i + "].links.self", equalTo(string));
				responseBodyDetails.assertThat().body("artObjects[" + i + "].objectNumber", equalTo(string2));
			}
		}
		System.out.println("Validation Completed.");
	}

}
