package homework;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Homework5 extends HomeworkBaseUrl{

     /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */
    @Test
    public void test05(){

        spec.pathParams("first", "unknown", "second", 3);


       Map<String, Object> expectedData = new HashMap<>();
       expectedData.put("id", 3);
       expectedData.put("name", "true red");
       expectedData.put("year", 2002);
       expectedData.put("color", "#BF1932");
       expectedData.put("pantone_value", "19-1664");

       Map<String, String> expectedSupport = new HashMap<>();
       expectedSupport.put("url", "https://reqres.in/#support-heading");
       expectedSupport.put("text", "To keep ReqRes free, contributions towards server costs are appreciated!");

       Map<String, Object> expected = new HashMap<>();
       expected.put("data", expectedData);
       expected.put("support", expectedSupport);


        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());

        assertEquals(expected.get("id"), ((Map)(actualData.get("id"))));
        assertEquals(expected.get("name"), ((Map)(actualData.get("name"))));
        assertEquals(expected.get("year"), ((Map)(actualData.get("year"))));
        assertEquals(expected.get("color"), ((Map)(actualData.get("color"))));
        assertEquals(expected.get("pantone_value"), ((Map)(actualData.get("pantone_value"))));
        assertEquals(expected.get("url"), ((Map)(actualData.get("url"))));
        assertEquals(expected.get("text"), ((Map)(actualData.get("text"))));






    }



    @Test
    public void test05b(){

        spec.pathParams("first", "unknown", "second", 3);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);


        JsonPath jsonPath = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();


        softAssert.assertEquals(jsonPath.getInt("data.id"), 3, "Id did not match");
        softAssert.assertEquals(jsonPath.getString("data.name"), "true red", "Name did not match");
        softAssert.assertEquals(jsonPath.getInt("data.year"), 2002, "Year did not match");
        softAssert.assertEquals(jsonPath.getString("data.color"), "#BF1932", "Color did not match");
        softAssert.assertEquals(jsonPath.getString("data.pantone_value"), "19-1664" , "Pantone_value did not match");
        softAssert.assertEquals(jsonPath.getString("support.url"), "https://reqres.in/#support-heading" , "URL did not match");
        softAssert.assertEquals(jsonPath.getString("support.text"), "To keep ReqRes free, contributions towards server costs are appreciated!" , "Text did not match");

        softAssert.assertAll();


    }

}
