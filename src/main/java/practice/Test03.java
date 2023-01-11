package practice;

import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Test03 extends DummyApiBaseUrl {

    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "[Tatyana Fitzpatrick]"
    And
        Total salary of all employees is 6,644,770
     */

    @Test
    public void test03(){

        spec.pathParam("first", "employees");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        response.then().assertThat().statusCode(200).body("data", hasSize(24), "employee_name", hasItems("Tiger Nixon", "Garrett Winters"));

        List<Integer> ages = jsonPath.getList("data.employee_age");
        Collections.sort(ages);
        assertEquals(66, (int)ages.get(ages.size()-1));

        String name = jsonPath.getString("data.findAll{it.employee_age==" + ages.get(0)+ "}.employee_name");
        assertEquals("[Tatyana Fitzpatrick]",name);

        List<Integer> salaries = jsonPath.getList("data.employee_salary");

        int sum = 0;

        for (int w : salaries){

            sum = sum + w;
        }

        int sum1 = salaries.stream().reduce(0, Math::addExact);
        int sum2 = salaries.stream().reduce(0, (t,u)-> (t+u));


    }

}
