package RijksmuseumAPI.page;

import org.junit.Assert;

import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.*;

public class CollectionImageAPI {

	public static void verifyResponseBodyinjsonarray(String value1, String value2) {
		ValidatableResponse responseBodyDetails = CollectionAPI.response.then();
		String object = responseBodyDetails.extract().body().jsonPath().getList(value2).get(0).toString();
		Assert.assertTrue(object.contains(value1));
		Assert.assertTrue(object.contains(value1));
		Assert.assertTrue(object.contains(value1));
		
		
		
	}

}
