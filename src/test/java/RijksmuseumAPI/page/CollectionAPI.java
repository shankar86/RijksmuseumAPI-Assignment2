package RijksmuseumAPI.page;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
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
		if (string2.equals("SK-A-4050")) {
			responseBodyDetails.assertThat().body("artObjects[0].links.self", equalTo(string));
			responseBodyDetails.assertThat().body("artObjects[0].objectNumber", equalTo(string2));
		} else if (string2.equals("SK-A-4691")) {
			responseBodyDetails.assertThat().body("artObjects[1].links.self", equalTo(string));
			responseBodyDetails.assertThat().body("artObjects[1].objectNumber", equalTo(string2));
		}
		System.out.println("Validation Completed");
	}

}
