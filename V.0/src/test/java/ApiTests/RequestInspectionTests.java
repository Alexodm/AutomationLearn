package ApiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.*;

import static io.restassured.RestAssured.given;


public class RequestInspectionTests {
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
    public void checkGetMethodHeaders() {
        given().spec(baseSpec()).when().get("headers")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkGetMethodIp(){
        given().spec(baseSpec()).when().get("ip")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkGetMethodUserAgent(){
        given().spec(baseSpec()).when().get("user-agent")
                .then().statusCode(HttpStatus.SC_OK);
    }


}
