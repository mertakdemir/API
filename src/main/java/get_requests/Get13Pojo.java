package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/100
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
                          {
    {
    "meta": null,
    "data": {
        "id": 100,
        "name": "Sujata Deshpande DC",
        "email": "deshpande_dc_sujata@rau-zulauf.io",
        "gender": "male",
        "status": "inactive"
    }
}
}
    */

    @Test
    public void get13(){

        //Set the URL
        spec.pathParams("first", "users", "second", 100);

        //Set the expected data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo("Sujata Deshpande DC", "deshpande_dc_sujata@rau-zulauf.io","male", "inactive");
        GoRestPojo expectedData = new GoRestPojo(null, goRestDataPojo);


        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //Do Assertion
        GoRestPojo actualData = response.as(GoRestPojo.class);

        assertEquals(200, response.statusCode());

        assertEquals(expectedData.getMeta(), actualData.getMeta());
        assertEquals(goRestDataPojo.getName(), actualData.getData().getName());
        assertEquals(goRestDataPojo.getEmail(), actualData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(), actualData.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(), actualData.getData().getStatus());



    }
}
