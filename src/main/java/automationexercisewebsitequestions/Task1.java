package automationexercisewebsitequestions;

import base_urls.AutomationExerciseBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Task1 extends AutomationExerciseBaseUrl {

    @Test
    public void test01(){

        spec.pathParam("first", "productsList");


        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType("text/html; charset=utf-8");
    }
}
