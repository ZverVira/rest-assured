package requestResponse;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RequestResponseTests {

    RequestSpecification reqSpecSingle = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in/")
            .setContentType(ContentType.JSON)
            .setBasePath("/api/users")
            .build();

    @Test
    public void getSingleUser() {

//        User actualUser = given()
//                        .spec(reqSpec)
//                        .pathParams("id", 8)
//                        .when().get()
//                .then().extract().body()
//                        .as(User.class);
//
//        User expectedUser = new User(
//                8,
//                "lindsay.ferguson@reqres.in",
//                "Lindsay",
//                "Ferguson",
//                "https://reqres.in/img/faces/8-image.jpg"
//        );
//
//        assertThat(actualUser, samePropertyValuesAs(expectedUser));

        given()
                .spec(reqSpecSingle)
                .queryParam("id", 8)
                .when()
                .get()
                .then()
                .log().body()
                .assertThat()
                    .statusCode(200)
                    .body("data.id", equalTo(8))
                    .body("data.email", equalTo("lindsay.ferguson@reqres.in"))
                    .body("data.first_name", equalTo("Lindsay"))
                    .body("data.last_name", equalTo("Ferguson"))
                    .body("data.avatar", equalTo("https://reqres.in/img/faces/8-image.jpg"));
    }

    @Test
    public void getNonExistSingleUser() {

       ValidatableResponse response = given()
                .spec(reqSpecSingle)
                .queryParam("id", 23)
                .when()
                .get()
                .then()
                .log().body()
                .assertThat()
                    .statusCode(404);
    }

    @Test
    public void createUser() {
        Person person = Person.builder()
                .name("morpheus")
                .job("leader")
                .build();

        ValidatableResponse response = given()
                .spec(reqSpecSingle)
                .body(person)
                .when()
                .post()
                .then()
                .log().body()
                .assertThat()
                    .statusCode(201)
                    .body( "name", equalTo("morpheus"))
                    .body( "job", equalTo("leader"));
        }

    @Test
    public void updateUser() {
        Person person = Person.builder()
                .name("morpheus")
                .job("zion resident")
                .build();

        ValidatableResponse response = given()
                .spec(reqSpecSingle)
                .queryParam("id", 2)
                .body(person)
                .when()
                .put()
                .then()
                .log().body()
                .assertThat()
                    .statusCode(200)
                    .body( "name", equalTo("morpheus"))
                    .body( "job", equalTo("zion resident"));
    }

    @Test
    public void deleteUser() {

        ValidatableResponse response = given()
                .spec(reqSpecSingle)
                .queryParam("id", 2)
                .when()
                .delete()
                .then()
                .log().body()
                .assertThat()
                    .statusCode(204);
    }

}

