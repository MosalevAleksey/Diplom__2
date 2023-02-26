package User;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
import static io.restassured.specification.ProxySpecification.auth;

public class UserStep {

    protected final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    protected final String ROOT = "/api/auth/register";
    protected final String LOGIN = "api/auth/login";
    protected final String CHANGE = "api/auth/user";

    public ValidatableResponse create(User user) {

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(ROOT)
                .then().log().all();

    }

    public ValidatableResponse login(Credentials creds) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all();

    }
    public ValidatableResponse changeUser(String token,User user) {

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .auth().oauth2(token.replace ("Bearer ","" ))
                .and()
                .body(user)
                .when()
                .patch(CHANGE)
                .then().log().all();

    }
    public ValidatableResponse changeUserWithOutAutoriz(User user) {

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .patch(CHANGE)
                .then().log().all();

    }
    public ValidatableResponse DeliteUser(String token,User user) {

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .auth().oauth2(token.replace ("Bearer ","" ))
                .and()
                .body(user)
                .when()
                .delete(CHANGE)
                .then().log().all();

    }
    public ValidatableResponse getInfoUser(String token) {

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .auth().oauth2(token.replace ("Bearer ","" ))
                .and()
                .when()
                .get(CHANGE)
                .then().log().all();

    }


   /* public void delete(int courierId) {
        String json = String.format("{\"id\": \"%d\"}", courierId);

        given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(json)
                .when()
                .delete(ROOT + "/" + courierId)
                .then().log().all();

    }*/

}
