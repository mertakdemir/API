package homework;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Homework6 extends HomeworkBaseUrl {

    /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */

    @Test
    public void test06(){

        spec.pathParam("first", "unknown");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();


        JsonPath jsonPath = response.jsonPath();
        List<String> pantone_values = jsonPath.getList("data.findAll{it.pantone_values}pantone_values");
        System.out.println(pantone_values);

        List<Integer> ids = jsonPath.getList("data.findAll{it.id>3}id");
        System.out.println(ids);
        assertEquals(3, ids.size());

        List<String> names = jsonPath.getList("data.findAll{it.id<3}name");
        System.out.println(names);
        assertEquals(2, names.size());




    }
}
