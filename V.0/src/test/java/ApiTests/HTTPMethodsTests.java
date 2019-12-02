package ApiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.*;

import static io.restassured.RestAssured.given;


public class HTTPMethodsTests {
    private RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON.withCharset("UTF-8"))
                .setBaseUri("http://httpbin.org/")
                .setPort(80)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void checkGetMethod() {
        given().spec(baseSpec()).when().get("/get")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkPutMethod() {
        given().spec(baseSpec()).when().put("/put")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkPostMethod() {
        given().spec(baseSpec()).when().post("/post")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkPatchMethod() {
        given().spec(baseSpec()).when().patch("/patch")
                .then().statusCode(HttpStatus.SC_OK);
    }

}
