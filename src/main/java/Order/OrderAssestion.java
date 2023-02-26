package Order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class OrderAssestion {
    @Step("Создание заказа")
    public void ordeСreationIsSuccessful(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("success", is(true));
    }

    @Step("Создание заказа не верные ингридиенты")
    public String  ordeСreationFald(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(500)
                .body("message", notNullValue())
                .extract()
                .asPrettyString();
    }
    @Step("Создание заказа пустые ингридиенты")
    public String  ordeСreationFald1(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(400)
                .body("message", notNullValue())
                .extract()
                .asPrettyString();
    }
    @Step("Получение информации о заказе конкретного пользователя")
    public String returnOrdeDataVereficationUser (ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("success", is(true))
                .extract()
                .asPrettyString();
    }
    @Step("Получение информации о заказе не авторизованого пользователя")
    public String returnOrdeDataDontVereficationUser (ValidatableResponse response) {
        return response.assertThat()
                .statusCode(401)
                .body("success", is(false))
                .extract()
                .asPrettyString();
    }
}
