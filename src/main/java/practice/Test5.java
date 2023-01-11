package practice;

import base_urls.GoRestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.JsonUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Test5 extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/177
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
         {
    "meta": null,
    "data": {
        "id": 177,
        "name": "Prayag Dwivedi",
        "email": "dwivedi_prayag@purdy-tillman.info",
        "gender": "male",
        "status": "inactive"
    }
}
     */

    @Test
    public void test05(){

        spec.pathParams("first", "users", "second", 177);


        Test5InnerPojo obj = new Test5InnerPojo("Prayag Dwivedi", "dwivedi_prayag@purdy-tillman.info", "male", "inactive");
        Test05Pojo expectedData = new Test05Pojo(null, obj);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().get("/{first}/{second}");
        response.prettyPrint();

        Test05Pojo actualData = JsonUtils.convertJsonToJava(response.asString(), Test05Pojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getMeta(), actualData.getMeta());
        assertEquals(expectedData.getData().getName(), actualData.getData().getName());
        assertEquals(expectedData.getData().getEmail(), actualData.getData().getEmail());
        assertEquals(expectedData.getData().getGender(), actualData.getData().getGender());
        assertEquals(expectedData.getData().getStatus(), actualData.getData().getStatus());


    }
}
