package APIModels;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GETWikipediaSummaryRequest {
    private static final String WIKIPEDIA_SUMMARY_ENDPOINT = "/page/summary/Wikipedia";

    public Response sendGetRequest() {
        return given()
                .when()
                .get(WIKIPEDIA_SUMMARY_ENDPOINT);
    }
}
