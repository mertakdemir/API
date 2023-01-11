package practice;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test2 extends JsonPlaceHolderBaseUrl {

    /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */
    @Test
    public void test02(){

        spec.pathParam("first", "todos");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();


        JsonPath jsonPath = response.jsonPath();

        List<Integer> id = jsonPath.getList("findAll{it.id>190}.id");
        System.out.println(id);
        assertEquals(10, id.size());

        List<Integer> userId = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println(userId);
        assertEquals(4, userId.size());

        List<String> titles = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println(titles);
        assertTrue(response.asString().contains("delectus aut autem"));


    }
}
