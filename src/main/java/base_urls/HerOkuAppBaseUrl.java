package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppBaseUrl {

    protected RequestSpecification spec;

    @Before//If you use "Before annotation" at the top of the method, it will be executed before every test method
    public void setUp() {
        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();


    }

}


