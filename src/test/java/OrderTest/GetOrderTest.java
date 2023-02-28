package OrderTest;

import Order.OrderAssestion;
import Order.OrderGenerater;
import Order.OrderStep;
import User.User;
import User.UserAssertion;
import User.UserGenerator;
import User.UserStep;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;

public class GetOrderTest {

    private final UserGenerator generator = new UserGenerator();
    private final UserStep client = new UserStep();
    private final UserAssertion check = new UserAssertion();
    private final OrderGenerater orderGenerater = new OrderGenerater();
    private final OrderStep clientOrder = new OrderStep();
    private final OrderAssestion checkOrder = new OrderAssestion();
    private String token;
    private User user;

    @DisplayName("Проверка создание заказа авторизованного пользователя")
    @Test
    public void returnOrderUserTest() {
        //Проверка создание заказа ответт 200
        user = generator.randow();//создаем объект с полями юзера
        ValidatableResponse creatResponse = client.create(user);//передаем данные пользователя на API создаём пользователя
        token = check.authorizationToken(creatResponse);// сохраням токен пользователя
        ValidatableResponse createOrder = clientOrder.createOrdre(orderGenerater.orderwithIngrediens(), token);//создаем заказ авторизованного пользователя передаем ингридиенты
        checkOrder.ordeСreationIsSuccessful(createOrder);/// проверяем что заказ создан ответ 200

        ValidatableResponse CheckUserOrder = clientOrder.CheckUserOrder(token);// Получаем от АПИ заказы авторизованного пользователя.

        String actual = checkOrder.returnOrdeDataVereficationUser(CheckUserOrder);
        MatcherAssert.assertThat(actual, containsString("\"60d3b41abdacab0026a733c6\",\n" +
                "                \"609646e4dc916e00276b2870\",\n" +
                "                \"61c0c5a71d1f82001bdaaa7a\""));//Проверяем сообщение содержит ингридиенты "("60d3b41abdacab0026a733c6"); //"Краторная булка N-200i","609646e4dc916e00276b2870");//"Говяжий метеорит (отбивная)","61c0c5a71d1f82001bdaaa7a"); //"Сыр с астероидной плесенью",


        ValidatableResponse deletUser = client.DeliteUser(token, user);// удаляем пользователя
    }

    @DisplayName("Проверка создание заказа авторизованного пользователя")
    @Test
    public void returnOrderDontAftorizationUserTest() {
        //Проверка создание заказа ответт 400

        ValidatableResponse CheckOrderDontAftorizationUser = clientOrder.CheckOrderDontAftorizationUser();// Получаем от АПИ заказы не авторизованного пользователя.
        String actual = checkOrder.returnOrdeDataDontVereficationUser(CheckOrderDontAftorizationUser);
        MatcherAssert.assertThat(actual, containsString("You should be authorised"));//Проверяем сообщение содержит ответ
    }
}

