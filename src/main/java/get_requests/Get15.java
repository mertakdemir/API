package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import test_data.HerOkuAppTestData;
import utils.JsonUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15 extends HerOkuAppBaseUrl{

     /*
        Given
                https://restful-booker.herokuapp.com/booking/555
        When
                I send GET Request to the URL
        Then
                Status code is 200
                       {
    "firstname": "Sarah",
    "lastname": "Hope",
    "totalprice": 250,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-11-27",
        "checkout": "2022-11-28"
    },
    "additionalneeds": "Coffee at 11 am"
}
     */

    @Test
    public void get15(){

        //Set the URL
        spec.pathParams("first", "booking", "second", 555);

        //Set the expected data
        String expectedDataInString = new HerOkuAppTestData().expectedDataInString("Sarah","Hope",250,true,"2022-11-27","2022-11-28", "Coffee at 11 am");
        BookingPojo expectedData = JsonUtils.convertJsonToJava(expectedDataInString, BookingPojo.class);
        System.out.println("expectedData = " + expectedData);


        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = JsonUtils.convertJsonToJava(response.asString(), BookingPojo.class);
        System.out.println(actualData);

        assertEquals(200, response.statusCode());

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());








    }
}
