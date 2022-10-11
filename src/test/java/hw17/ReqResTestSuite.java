package hw17;

import hw17.requestBodies.CreateUserRequestBody;
import hw17.requestBodies.RegisterUserRequestBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class ReqResTestSuite {

    @Test
    public void listUsersByIdLengthIsEqualSix(){

        given()
                .log().uri()
        .when()
                .get("https://reqres.in/api/users?page=1")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data", hasSize(6));
    }

    @CsvSource(value = {
            "obivan,kenobi",
            "leonardo,dikaprio",
            "vasya,vasin"
    })
    @ParameterizedTest
    public void createUser(String username, String job){
        CreateUserRequestBody createUserRequestBody = new CreateUserRequestBody(username, job);

        given()
                .contentType("application/json")
                .body(createUserRequestBody)
                .log().uri()
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .log().status()
                .log().body()
                .body("username", is(username),
                        "job", is(job));
    }

    @Test
    public void userNotFound(){
        given()
                .log().uri()
        .when()
                .get("https://reqres.in/api/users/23")
        .then()
                .log().status()
                .statusCode(404);
    }

    @CsvSource(value = {
            "eve.holt@reqres.in,qwe123"
    })
    @ParameterizedTest
    public void registerUser(String email, String password){
        RegisterUserRequestBody registerUserRequestBody = new RegisterUserRequestBody(email, password);

        given()
                .log().uri()
                .contentType("application/json")
                .body(registerUserRequestBody)
        .when()
                .post("https://reqres.in/api/register")
        .then()
                .log().status()
                .statusCode(200)
                .body("id", is(notNullValue()),
                        "token", is(notNullValue()));
    }

    @CsvSource(value = {
            "eve.holt@reqres.in,qwe123"
    })
    @ParameterizedTest
    public void loginUser(String email, String password){
        RegisterUserRequestBody registerUserRequestBody = new RegisterUserRequestBody(email, password);

        given()
                .log().uri()
                .contentType("application/json")
                .body(registerUserRequestBody)
        .when()
                .post("https://reqres.in/api/login")
        .then()
                .log().status()
                .statusCode(200)
                .body("token", is(notNullValue()));
    }


}
