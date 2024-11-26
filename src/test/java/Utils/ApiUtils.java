package Utils;

import io.restassured.response.Response;

public class ApiUtils {

    public static void validateResponseContainsKeys(Response response) {
        // Parse the response as JSON
        var jsonResponse = response.jsonPath();
    }
}