package ApiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.*;

import static io.restassured.RestAssured.given;


public class StatusCodesTests {
    private RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON.withCharset("UTF-8"))
                .setBaseUri("http://httpbin.org/status/")
                .setPort(80)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void checkDeleteMethod() {
        given().spec(baseSpec()).when().delete("200")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkGetMethod() {
        given().spec(baseSpec()).when().get("400")
                .then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void checkPatchMethod(){
        given().spec(baseSpec()).when().patch("500")
                .then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void checkPostMethod(){
        given().spec(baseSpec()).when().post("300")
                .then().statusCode(HttpStatus.SC_MULTIPLE_CHOICES);
    }

    @Test
    public void checkPutMethod(){
        given().spec(baseSpec()).when().put("200")
                .then().statusCode(HttpStatus.SC_OK);
    }

}
