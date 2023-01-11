package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {

    /*
       Given
           https://gorest.co.in/public/v1/users
       When
           User send GET Request
       Then
           The value of "pagination limit" is 10
       And
           The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
       And
           The number of users should  be 10
       And
           We have at least one "active" status
       And
           "Dr. Bhagwati Mehrotra, Heema Singh, Rep. Dayaamay Iyer" are among the users
       And
           The female users are less than or equal to male users
    */


    @Test
    public void get11(){

        //Set the URL
        spec.pathParam("first", "users");

        //Set the expected data


        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();


        //Do Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit", equalTo(10)).
                body("meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1")).
                body("data.name", hasSize(10)).
                body("data.status", hasItem("active")).
                body("data.name", hasItems("Dr. Bhagwati Mehrotra", "Heema Singh", "Rep. Dayaamay Iyer"));




        //The female users are less than or equal to male users
        //I will compare number of female and male users
        //1st way: I will get all genders in a list then calculate the number of females then compare it with the list

        JsonPath jsonPath = response.jsonPath();
        List<String> gender = jsonPath.getList("data.gender");
        System.out.println(gender);

        int numOfFemales = 0;
        for (String w : gender){
            if (w.equalsIgnoreCase("female")){
                numOfFemales++;
            }

        }
        System.out.println(numOfFemales);

        int numOfMales = gender.size() - numOfFemales;

        assertTrue(numOfFemales <= numOfMales);


        //2nd Way: I will get all females by using "Groovy Language" then compare it with males
        List<String> femaleList = jsonPath.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println(femaleList);


        List<String> maleList = jsonPath.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println(maleList);

        assertTrue(femaleList.size() <= maleList.size());






    }
}
