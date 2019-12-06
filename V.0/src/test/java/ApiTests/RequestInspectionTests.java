package ApiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;


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
                .then()
                .statusCode(HttpStatus.SC_OK)
                .header("connection","keep-alive");
    }

    @Test
    public void checkGetMethodIp(){
        given().spec(baseSpec()).when().get("ip")
                .then().statusCode(HttpStatus.SC_OK)
                .body("origin",notNullValue());
    }

    @Test
    public void checkGetMethodUserAgent(){
        given().spec(baseSpec()).when().get("user-agent")
                .then().statusCode(HttpStatus.SC_OK);
    }


}
