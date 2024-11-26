package API;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class APIBaseTest {

    @BeforeClass
    public void setup() {
        // Set the base URL for API tests
        RestAssured.baseURI = Configurations.APIConfiguration.getBaseURL();
    }
}
