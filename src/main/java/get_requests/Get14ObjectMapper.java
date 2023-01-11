package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper extends JsonPlaceHolderBaseUrl {

      /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
                                        "id":198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void get14(){

        //Set the URL
        spec.pathParams("first", "todos", "second", 198);


        //Set the expected data
        /*String expectedDataInString = "" +
                "{ " +
                "\"userId\": 10, " +
                " \"title\": \"quis eius est sint explicabo\", " +
                "\"completed\": true }";
                /*
         */

        //Set the expected data
        String expectedDataInString = new JsonPlaceHolderTestData().expectedDataInString(10, "quis eius est sint explicabo", true);


        //Send the request and get the response
        Map<String, Object> expectedData = JsonUtils.convertJsonToJava(expectedDataInString, HashMap.class);
        System.out.println(expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String, Object> actualData = JsonUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println(actualData);


        assertEquals(200, response.statusCode());

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }
}
