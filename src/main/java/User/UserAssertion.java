package User;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class UserAssertion {
    @Step("Создание пользователя")
    public void createdSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("success", is(true));
    }

    @Step("Ошибка создание двух пользователей с одинаковыми полями")
    public String creationFaildLoginRepeated(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(403)
                .body("message", notNullValue())
                .extract()
                .path("message");
    }

    @Step("Ошибка создание пользователя поле не заполнено ")
    public String creationFaildRequiredFields(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(403)
                .body("message", notNullValue())
                .extract()
                .path("message");
    }


    @Step("Log in пользователя OK")
    public void loggedInSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("success", is(true));
    }

    @Step("Log in пользователя невозможно  не правельный email password")
    public String loggedInFale(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(401)
                .body("message", notNullValue())
                .extract()
                .path("message");
    }

    @Step("Проверка изменения данных пользователя ОК")
    public void userChangeOk(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("success", is(true));
    }

    @Step("Проверка изменения данных пользователя Unauthorized")
    public String userChangeUnauthorized(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(401)
                .body("message", notNullValue())
                .extract()
                .path("message");
    }

    @Step("Получениие токенаTokenrd")
    public String authorizationToken(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("accessToken", notNullValue())
                .extract()
                .path("accessToken");
    }

}
