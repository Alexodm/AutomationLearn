package ApiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class AuthTests {
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
    public void checkBasicAuth() {
        given().spec(baseSpec()).auth().basic("user", "passwd")
                .when().get("basic-auth/user/passwd")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkBearerAuth() {
        given().spec(baseSpec()).auth().preemptive().oauth2("Authorisation")
                .when().get("bearer")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkDigestAuth() {
        given().spec(baseSpec()).auth().digest("user", "passwd")
                .when().get("digest-auth/undefined/user/passwd")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkDigestAuthWithAlg() {
        given().spec(baseSpec()).auth().digest("user", "passwd")
                .when().get("digest-auth/undefined/user/passwd/MD5")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkDigestAuthWithStale() {
        given().spec(baseSpec()).auth().digest("user", "passwd")
                .when().get("digest-auth/undefined/user/passwd/MD5/never")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkHiddenBasicAuth() {
        given().spec(baseSpec()).auth().preemptive().basic("user", "passwd")
                .formParam("user", "user")
                .formParam("passwd", "passwd")
                .when().get("hidden-basic-auth/user/passwd/")
                .then().statusCode(HttpStatus.SC_OK);
    }

}
