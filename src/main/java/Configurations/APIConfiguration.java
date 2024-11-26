package Configurations;

import lombok.Getter;

public class APIConfiguration {
    @Getter
    private static String baseURL = "https://en.wikipedia.org/api/rest_v1";

    // Setter for Base URL (if it needs to be updated dynamically)
    public static void setBaseURL(String baseURL) {
        APIConfiguration.baseURL = baseURL;
    }
}