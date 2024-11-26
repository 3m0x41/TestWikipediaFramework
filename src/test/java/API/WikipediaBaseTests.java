package API;

import APIModels.GETWikipediaSummaryRequest;
import APIModels.GETWikipediaSummaryResponse;
import Common.TestCategories;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WikipediaBaseTests extends APIBaseTest {

    @Test
    @Parameters(TestCategories.API_SMOKE_TESTS)
    public void testWikipediaSummaryEndpoint() throws Exception {
        // Create request instance and send GET request
        GETWikipediaSummaryRequest GETWikipediaSummaryRequest = new GETWikipediaSummaryRequest();
        Response response = GETWikipediaSummaryRequest.sendGetRequest();

        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");

        // Deserialize response to WikipediaSummaryResponse
        ObjectMapper objectMapper = new ObjectMapper();
        GETWikipediaSummaryResponse apiResponse = objectMapper.readValue(response.asString(), GETWikipediaSummaryResponse.class);

        // Validate fields in the response
        validateApiResponse(apiResponse);
    }

    private void validateApiResponse(GETWikipediaSummaryResponse apiResponse) {
        Assert.assertEquals(apiResponse.getTitle(), "Wikipedia", "Title does not match");
        Assert.assertNotNull(apiResponse.getDescription(), "Description is missing");
        Assert.assertNotNull(apiResponse.getExtract(), "Extract is missing");
    }
}