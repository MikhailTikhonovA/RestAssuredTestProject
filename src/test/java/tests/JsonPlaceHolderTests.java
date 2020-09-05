package tests;

import config.TestConfig;
import org.junit.jupiter.api.Test;

import static constants.Constants.Actions.*;
import static io.restassured.RestAssured.given;

public class JsonPlaceHolderTests extends TestConfig {

    @Test
    public void get() {
        given().queryParam("postId", 1).log().uri().
                when().get(JSON_PLACEHOLDER_GET).
                then().log().body().statusCode(200);
    }

    @Test
    public void put() {
        String putBody = "{\n" +
                "\"id\" : 1,\n" +
                "\"title\" : foo,\n" +
                "\"body\" : bar,\n" +
                "\"userId\" : 1\n" +
                "}";
        given().body(putBody).log().uri().
                when().put(JSON_PLACEHOLDER_PUT).
                then().log().body().statusCode(200);
    }

    @Test
    public void delete() {
        given().log().uri()
                .when().delete(JSON_PLACEHOLDER_DELETE)
                .then().log().body().statusCode(200);
    }

    @Test
    public void postWithJson() {
        String postBodyJson = "{\n" +
                "\"title\" : \"foo\",\n" +
                "\"body\" : \"bar\",\n" +
                "\"userId\" : 1\n" +
                "}";
        given().body(postBodyJson).log().all().
                when().post(JSON_PLACEHOLDER_POST).
                then().spec(responseSpecificationForPost).log().body();
    }

    @Test
    public void postWithXml() {
        String postBodyXml = "<?xml version=\"1.0\"?>\n" +
                "<Company>\n" +
                "  <Employee>\n" +
                "      <FirstName>Tanmay</FirstName>\n" +
                "      <LastName>Patil</LastName>\n" +
                "      <ContactNo>1234567890</ContactNo>\n" +
                "      <Email>tanmaypatil@xyz.com</Email>\n" +
                "      <Address>\n" +
                "           <City>Bangalore</City>\n" +
                "           <State>Karnataka</State>\n" +
                "           <Zip>560212</Zip>\n" +
                "      </Address>\n" +
                "  </Employee>\n" +
                "</Company>";
        given().spec(requestSpecificationXml).body(postBodyXml).log().all().
                when().post("").
                then().spec(responseSpecificationForGet).log().body();
    }
}
