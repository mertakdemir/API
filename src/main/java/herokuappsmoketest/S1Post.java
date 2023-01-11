package herokuappsmoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utils.JsonUtils;

import java.lang.module.ResolutionException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S1Post extends HerOkuAppBaseUrl{

    /*Type an automation smoke test by using "https://restful-booker.herokuapp.com/apidoc/index.html" documentation.
     Create a booking then update, read and delete the booking you created.
    */

    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
        {
"firstname" : "Jim",
"lastname" : "Brown",
"totalprice" : 111,
"depositpaid" : true,
"bookingdates" : {
"checkin" : "2018-01-01",
"checkout" : "2019-01-01"
},
"additionalneeds" : "Breakfast"
}

    When
        User sends POST request and get the response

   Then
        Status code must be 200

   And

        Response body is like :
        {
    "bookingid": 18139,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}

     */

    static int bookingid;
    @Test
    public void post01(){

        //Set the URL
        spec.pathParam("first", "booking");

        //Set the expected data
        BookingDatesPojo obj = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Jim", "Brown", 111, true, obj, "Breakfast");
        System.out.println(expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do Assertion
        BookingResponsePojo actualData = JsonUtils.convertJsonToJava(response.asString(), BookingResponsePojo.class);
        System.out.println(actualData);

        assertEquals(200, response.statusCode());

        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        assertEquals(obj.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(obj.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());

        bookingid = actualData.getBookingId();

    }


}
