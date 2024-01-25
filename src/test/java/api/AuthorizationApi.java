package api;

import models.LoginBody;
import models.LoginResponse;

import static io.restassured.RestAssured.given;
import static specs.Specification.successfulLoginResponse;
import static specs.Specification.loginRequest;


public class AuthorizationApi {

    public static LoginResponse authResponse () {
        LoginBody userData = new LoginBody();
        userData.setUserName("Iskander");
        userData.setPassword("Flybyway1!");

        return
                given(loginRequest)
                        .body(userData)
                .when()
                        .post()
                .then()
                        .log().all()
                        .spec(successfulLoginResponse)
                        .extract().as(LoginResponse.class);
    };
}
