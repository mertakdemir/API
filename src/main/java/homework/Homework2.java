package homework;

import io.restassured.response.Response;
import io.restassured.specification.Argument;
import org.junit.Test;

import javax.print.DocFlavor;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Homework2 extends HomeworkBaseUrl {

     /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty

     */

    @Test
    public void test02(){



        try {

            spec.pathParams("first", "users", "second", 23);


            Response response = given().spec(spec).when().get("/{first}/{second}");
            response.prettyPrint();

            response.then().assertThat().statusCode(404).statusLine(" HTTP/1.1 404 Not Found");
            assertEquals("cloudflare", response.getHeader("Server"));
            assertTrue(response.asString().isEmpty());


        }catch (Exception e){
            System.out.println("Not found");
        }

        }
    }

