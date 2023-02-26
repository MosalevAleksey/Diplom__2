package Order;

import User.User;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class OrderStep {
    protected final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    protected final String ORDER = "/api/orders";


    public ValidatableResponse createOrdre(Ingredients ingredients, String token) {

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .auth().oauth2(token.replace("Bearer ", ""))
                .and()
                .body(ingredients)
                .when()
                .post(ORDER)
                .then().log().all();
    }

    public ValidatableResponse createOrdreWithoutAftorizetion(Ingredients ingredients) {

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(ingredients)
                .when()
                .post(ORDER)
                .then().log().all();
    }

    public ValidatableResponse CheckOrderDontAftorizationUser() {

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .and()
                .when()
                .get(ORDER)
                .then().log().all();
    }

    public ValidatableResponse CheckUserOrder(String token) {

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .auth().oauth2(token.replace("Bearer ", ""))
                .and()
                .when()
                .get(ORDER)
                .then().log().all();
    }

}
