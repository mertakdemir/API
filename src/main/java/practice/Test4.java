package practice;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.JsonUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Test4 extends JsonPlaceHolderBaseUrl {

       /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false

            }
     */


    @Test
    public void test03(){

        spec.pathParams("first", "todos", "second", 2);

        Test4Pojo expectedData = new Test4Pojo(1, "quis ut nam facilis et officia qui", false);


        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().get("/{first}/{second}");
        response.prettyPrint();

        Test4Pojo actualData = JsonUtils.convertJsonToJava(response.asString(), Test4Pojo.class);

        assertEquals(200, response.statusCode());
        assertEquals("1.1 vegur", response.getHeader("Via"));
        assertEquals("cloudflare", response.getHeader("Server"));
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());


    }

}
