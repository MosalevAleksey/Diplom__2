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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

public class CreateOrderTest {


    private final UserGenerator generator = new UserGenerator();
    private final UserStep client = new UserStep();
    private final UserAssertion check = new UserAssertion();
    private final OrderGenerater orderGenerater = new OrderGenerater();
    private final OrderStep clientOrder = new OrderStep();
    private final OrderAssestion checkOrder = new OrderAssestion();
    private String token;
    private User user;

    @Before
    public void createUser() {
        user = generator.randow();//создаем объект с полями юзера
        ValidatableResponse creatResponse = client.create(user);//передаем данные пользователя на API создаём пользователя
        token = check.authorizationToken(creatResponse);// сохраням токен пользователя
    }

    @After
    public void deletCourer() {
        ValidatableResponse deletUser = client.DeliteUser(token, user);// удаляем пользователя
    }

    @DisplayName("Проверка создание заказа авторизованного пользователя")
    @Test
    public void orderCreataOK() {
        //Проверка создание заказа ответт 200
        ValidatableResponse createOrder = clientOrder.createOrdre(orderGenerater.orderwithIngrediens(), token);//создаем заказ авторизованного пользователя передаем ингридиенты
        checkOrder.ordeСreationIsSuccessful(createOrder);/// проверяем что заказ создан ответ 200
    }

    @DisplayName("Проверка создание заказа авторизованного пользователя не правильные ингридиенты")
    @Test
    public void orderCreataFaildRongIngridients() {
        //Проверка создание заказа ответт 500 и Internal Server Error
        ValidatableResponse createOrder = clientOrder.createOrdre(orderGenerater.orderRongIngrediens(), token);//создаем заказ авторизованного пользователя передаем ингридиенты
        String message = checkOrder.ordeСreationFald(createOrder);
        MatcherAssert.assertThat(message, containsString("Internal Server Error"));//Проверяем ответ 500 сообщение содержит "Internal Server Error"
    }

    @DisplayName("Проверка создание заказа авторизованного пользователя Пустые ингридиенты")
    @Test
    public void orderCreataFaildWithoutIngredients() {
        //Проверка создание заказа ответт 400 и Ingredient ids must be provided.
        ValidatableResponse createOrder = clientOrder.createOrdre(orderGenerater.orderwithOutIngrediens(), token);//создаем заказ авторизованного пользователя передаем ингридиенты
        String message = checkOrder.ordeСreationFald1(createOrder);
        MatcherAssert.assertThat(message, containsString("Ingredient ids must be provided"));//Проверяем ответ 400 сообщение содержит ""Ingredient ids must be provided."
    }

    @DisplayName("Проверка создание заказа не автаризованного пользователя")
    @Test
    public void orderCreataWithoutAftorizetion() {
        //Проверка создание заказа  создание заказа не автаризованного пользователя ответт 200

        ValidatableResponse createOrder = clientOrder.createOrdreWithoutAftorizetion(orderGenerater.orderwithIngrediens());//создаем заказ не авторизованного пользователя передаем ингридиенты
        checkOrder.ordeСreationIsSuccessful(createOrder);/// проверяем что заказ создан ответ 200
    }

}

