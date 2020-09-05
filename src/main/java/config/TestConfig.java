package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;

import static constants.Constants.RunVariable.path;
import static constants.Constants.RunVariable.server;
import static constants.Constants.Servers.REQUEST_BIN;
import static constants.Constants.Servers.SWAPI_URL;

public class TestConfig {


    protected RequestSpecification requestSpecificationXml = new RequestSpecBuilder()
            .setBaseUri(REQUEST_BIN)
            .addHeader("Content-Type", "application/xml")
            .addCookie("testCookieXml")
            .build();

    protected ResponseSpecification responseSpecificationForGet = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    protected ResponseSpecification responseSpecificationForPost = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();

    protected RequestSpecification requestSpecificationForSwapi = new RequestSpecBuilder()
            .setBaseUri(SWAPI_URL)
            .build();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = server;
        RestAssured.basePath = path;

        RequestSpecification requestSpecificationJson = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addCookie("testCookieJson")
                .build();

        RestAssured.requestSpecification = requestSpecificationJson;
    }
}
