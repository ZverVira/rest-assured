package validateSchema;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ContractTest {

    RequestSpecification reqSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in/")
            .setContentType(ContentType.JSON)
            .setBasePath("/api/users")
            .build();

    @Test
    public void checkContract() {
        given()
                .baseUri("https://reqres.in/")
                .contentType(ContentType.JSON)
                .basePath("/api/users")
                .param("page",2)
                .when()
                .get()
                .then()
                .log()
                .body()
                .body(matchesJsonSchemaInClasspath("jsonResponseSchema.json"));
    }
    @Test
    public void checkContractWithSpecification() {
        given()
                .spec(reqSpec)
                .when()
                .get()
                .then()
                .log()
                .body()
                .body(matchesJsonSchemaInClasspath("jsonResponseSchema.json"));
    }
}


