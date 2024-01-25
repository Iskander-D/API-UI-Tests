package api;

import models.LoginBody;
import models.LoginResponse;

import static data.UserData.PASSWORD;
import static data.UserData.USER_NAME;
import static io.restassured.RestAssured.given;
import static specs.Specification.*;


public class AuthorizationApi {

    public static LoginResponse authResponse () {
        LoginBody userData = new LoginBody();
        userData.setUserName(USER_NAME);
        userData.setPassword(PASSWORD);

        return
                given(allRequests)
                        .body(userData)
                .when()
                        .post("/Account/v1/Login")
                .then()
                        .log().all()
                        .spec(successfulLoginResponse200)
                        .extract().as(LoginResponse.class);
    };
}
