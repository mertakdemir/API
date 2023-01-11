package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;


public class Get01 {
    /*
    1)Postman is used for manuel API testing.
    2)We use Rest-Assured library for API Automation Testing
    3)To type automation script follow the given steps:
        a)Understand the requirement
        b)Type test cases
            To type test cases we use "Gherkin Language"
            The keywords are     1)Given: It is for pre-condition
                                 2)When:  It is for actions.
                                 3)Then:  It is for output
                                 4)And:   It is for multiple given, when and then

        c)Start to type Automation script
          i) Set the URL
          ii)Set the expected Data(Post-Put-Patch)
          iii)Type code to send request
          IV) Do assertion

     */

      /*
   Given
       https://restful-booker.herokuapp.com/booking/10
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
      */


    @Test
    public void get01(){
        //Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/10";

        //Set the expected data



        //Set the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();



        //Do Assertion


        //HTTP Status Code should be 200
        //Content type should be JSON
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");


        //How to print "Status Code" on the console
        System.out.println("Status Code: " + response.getStatusCode());


        //How to print "Content Type" on the console
        System.out.println("Content Type: " + response.getContentType());

        //How to print "Status Line" on the console
        System.out.println("Status Line: " + response.getStatusLine());



        //How to print "Header" on the console
        System.out.println(response.getHeader("Server"));


        //How to print "Headers" on the console
        System.out.println(response.getHeaders());

        //How to print "Time" on the console
        System.out.println(response.getTime());






    }







}
