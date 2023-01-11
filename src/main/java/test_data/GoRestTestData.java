package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {


    public Map<String,String> goRestDataMap(String name, String email, String gender, String status){

        Map<String, String> goRestDataMap = new HashMap<>();
        goRestDataMap.put("name", name);
        goRestDataMap.put("email", email);
        goRestDataMap.put("gender", gender);
        goRestDataMap.put("status", status);

        return goRestDataMap;


    }

    public Map<String, Object> expectedDataMap(Object meta, Map<String, String> data){

        Map<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("meta", meta);
        expectedDataMap.put("data", data);

        return expectedDataMap;

    }

    /*
       {
    "meta": null,
    "data": {
        "name": "Suresh Johar",
        "email": "suresh_johar@von-damore.biz",
        "gender": "female",
        "status": "active"
    }
}

     */
}
