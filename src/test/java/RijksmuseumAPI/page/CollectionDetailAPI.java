package RijksmuseumAPI.page;

import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.*;

public class CollectionDetailAPI {

	public static void verifyResponseBody(String value1, String value2) {
		ValidatableResponse responseBodyDetails = CollectionAPI.response.then();
		if (value1.equals("null")) {
			responseBodyDetails.assertThat().body(value2, nullValue());
		} else {
			responseBodyDetails.assertThat().body(value2, containsString(value1));
		}
	}

}
