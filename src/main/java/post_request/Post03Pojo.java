package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {

    /*
         Given
            1) https://jsonplaceholder.typicode.com/todos
            2)  {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */






    @Test
    public void post03(){

        //Set the URL
        spec.pathParam("first", "todos");


        //Set the expected data
        JsonPlaceHolderPojo obj = new JsonPlaceHolderPojo(55, "Tidy your room", false);
        System.out.println(obj);


        //Send the request and get the response
        Response response = given().spec(spec).body(obj).contentType(ContentType.JSON).when().post("/{first}");
        response.prettyPrint();


        //Do Assertion
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);//De-Serialization

        assertEquals(201, response.statusCode());
        assertEquals(obj.getUserId(), actualData.getUserId());
        assertEquals(obj.getTitle(), actualData.getTitle());
        assertEquals(obj.getCompleted(), actualData.getCompleted());

    }
}
