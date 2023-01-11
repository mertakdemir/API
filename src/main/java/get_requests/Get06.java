package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {


     /*
        Given
            https://restful-booker.herokuapp.com/booking/20
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
              {
    "firstname": "Edgar",
    "lastname": "Combs",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */


    @Test
    public void get06(){

        //Set the URL
        spec.pathParams("first", "booking", "second", 20);


        //Set the expected data



        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //Do Assertion
        //1st Way:
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname", equalTo("Edgar"),
                        "lastname", equalTo("Combs"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo(2018-01-01),
                        "bookingdates.checkout", equalTo(2019-01-01),
                        "additionalneeds", equalTo("Breakfast"));


        //2nd Way: We will use JsonPath Class
        JsonPath jsonPath = response.jsonPath();

        assertEquals("Edgar", jsonPath.getString("firstname"));
        assertEquals("Combs", jsonPath.getString("lastname"));
        assertEquals(111, jsonPath.getInt("totalprice"));
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast", jsonPath.getString("additionalneeds"));



        //Soft Assertion
        //To do soft assertion follow these 3 steps
        //1st: Create SoftAsser Object
        SoftAssert softAssert = new SoftAssert();



        //2nd: Do Assertion
        softAssert.assertEquals(jsonPath.getString("firstname"), "Edgar", "firstname did not match");
        softAssert.assertEquals(jsonPath.getString("lastname"), "Combs", "lastname did not match");
        softAssert.assertEquals(jsonPath.getInt("totalprice"), 111, "totalprice did not match");
        softAssert.assertEquals(jsonPath.getBoolean("depositpaid"), true, "depositpaid did not match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01", "checkin did not match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01", "checkout did not match");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"), "Breakfast", "additionalneeds did not match");



        //3rd:Use "assertAll()" method
        softAssert.assertAll();



























    }
}
