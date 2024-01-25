package api;

import models.LoginBody;
import models.LoginResponse;

import static data.UserData.PASSWORD;
import static data.UserData.USER_NAME;
import static io.restassured.RestAssured.given;
import static specs.Specification.successfulLoginResponse;
import static specs.Specification.loginRequest;


public class AuthorizationApi {

    public static LoginResponse authResponse () {
        LoginBody userData = new LoginBody();
        userData.setUserName(USER_NAME);
        userData.setPassword(PASSWORD);

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
