package tests;


import config.TestConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static constants.Constants.Actions.SWAPI_GET_PEOPLE;
import static constants.Constants.Path.SWAPI_PATH;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class ApiTests extends TestConfig {

    @Test
    public void test() {
        given().log().uri().
                when().get(SWAPI_GET_PEOPLE + "1").
                then().log().body().statusCode(200);
    }

    @Test
    public void checkFieldTest() {
        given().spec(requestSpecificationForSwapi).log().uri().
                when().get(SWAPI_PATH).
                then().body("people", equalTo("http://swapi.dev/api/people/")).log().body();
    }

    @Test
    public void checkFieldWithIndexTest() {
        given().spec(requestSpecificationForSwapi).log().uri().
                when().get(SWAPI_PATH + SWAPI_GET_PEOPLE).
                then()
                .body("count", equalTo(82))
                .body("results.name[0]", equalTo("Luke Skywalker"))
                .log().body();
    }

    @Test
    public void getAllDataFromRequest() {
        Response response = given().spec(requestSpecificationForSwapi).log().uri().
                when().get(SWAPI_PATH).
                then().extract().response();

        String jsonResponse = response.asString();
        System.out.println(jsonResponse);
    }

    @Test
    public void getCookieFromResponse() {
        Response response = given().spec(requestSpecificationForSwapi).log().uri().
                when().get(SWAPI_PATH).
                then().extract().response();
        Map<String, String> allCookie = response.getCookies();
        System.out.println(allCookie);
    }

    @Test
    public void getHeadersFromResponse() {
        Response response = given().spec(requestSpecificationForSwapi).log().uri().
                when().get(SWAPI_PATH).
                then().extract().response();

        Headers headers = response.getHeaders();
        System.out.println(headers);

        String contentType = response.getContentType();
        System.out.println(contentType);
    }

    @Test
    public void validateJsonSchema() {
        given().header("x-rapidapi-host", "covid-19-data.p.rapidapi.com")
                .header("x-rapidapi-key", "272142d0bbmsh1fe0c87b5b6977bp1ccc27jsn2c733d613181")
                .log().uri()
                .when().get("https://covid-19-data.p.rapidapi.com/totals?format=json")
                .then().body(matchesJsonSchemaInClasspath("covidJsonScheme.json")).log().body();
    }

    @Test
    public void getMapOfElements() {
        Response response = given().spec(requestSpecificationForSwapi).log().uri().
                when().get(SWAPI_PATH + SWAPI_GET_PEOPLE);
        //System.out.println(response.asString());
        Map<String, ?> map = response
                .path("results.find{it.name='Luke Skywalker'}");

        System.out.println(map);
    }

    @Test
    public void getSingleElement() {
        Response response = given().spec(requestSpecificationForSwapi).log().uri().
                when().get(SWAPI_PATH + SWAPI_GET_PEOPLE);

        String url = response.
                path("results.find{it.name='Luke Skywalker'}.url");

        System.out.println(url);
    }

    @Test
    public void getAllElements() {
        Response response = given().spec(requestSpecificationForSwapi).log().uri().
                when().get(SWAPI_PATH + SWAPI_GET_PEOPLE);
        List films = response.path("results.findAll {it.films}.name");
        System.out.println(films);
    }
}
