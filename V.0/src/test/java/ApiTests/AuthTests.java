package ApiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.*;

import static io.restassured.RestAssured.given;


public class AuthTests {
    private RequestSpecification baseSpec(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON.withCharset("UTF-8"))
                .setBaseUri("http://httpbin.org/")
                .setPort(80)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void checkGetMethod(){
        given().auth().preemptive().basic("user", "passwd")
                .when().get("http://httpbin.org/basic-auth/undefined/undefined")
                .then().statusCode(HttpStatus.SC_OK);


//        given().auth()
//                .basic("user", "passwd")
//                .when()
//                .get("http://httpbin.org/basic-auth/undefined/undefined")
//                .then()
//                .statusCode(HttpStatus.SC_OK);
    }

}
