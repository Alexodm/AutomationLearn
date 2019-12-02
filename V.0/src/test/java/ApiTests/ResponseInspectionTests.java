package ApiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ResponseInspectionTests {
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
    public void CheckGetCacheMethod(){
        given().spec(baseSpec()).when().get("cache")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void CheckGetCacheValueMethod(){
        given().spec(baseSpec()).when().get("cache/200")
                .then().statusCode(HttpStatus.SC_OK).header("Cache-Control","public, max-age=200");
    }
}
