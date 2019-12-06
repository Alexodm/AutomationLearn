package ApiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Test;


import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;

public class ResponseFormatsTests {
    private RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON.withCharset("UTF-8"))
                .setBaseUri("https://httpbin.org/")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void checkGetBrotliMethod() {
        given().spec(baseSpec()).when().get("brotli")
                .then().statusCode(HttpStatus.SC_OK).header("content-encoding", "br");
    }

    @Test
    public void checkDeflate() {
        given().spec(baseSpec()).when().get("deflate")
                .then().statusCode(HttpStatus.SC_OK).body("deflated", equalTo(true));
    }

    @Test
    public void checkGetDenyMethod(){
        given().spec(baseSpec()).when().get("deny")
                .then().statusCode(HttpStatus.SC_OK).body(containsString("YOU SHOULDN'T BE HERE"));
    }

}