package UserTest;


import User.User;
import User.UserAssertion;
import User.UserGenerator;
import User.UserStep;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangingUserDataTest {
    private final UserGenerator generator = new UserGenerator();
    private final UserStep client = new UserStep();
    private final UserAssertion check = new UserAssertion();

    private String token;
    private User user;

    @Before
    public void createUser() {
        user = generator.randow();//создаем объект с полями юзера

    }

    @After
    public void deletCourer() {
        ValidatableResponse deletUser = client.DeliteUser(token, user);// удаляем пользователя
    }

    @DisplayName("Проверка изменения данных авторизованного пользователя  Email ответт 200 и success= true")
    @Test
    public void userChangeEmailOK() {
        //Проверка создание пользователя ответт 200  success= true
        ValidatableResponse creatResponse = client.create(user);//передаем данные пользователя на API создаём пользователя
        token = check.authorizationToken(creatResponse);// сохраням токен пользователя
        user.setEmail(generator.randowEmail());//меняем пользователю новый е майл

        // System.out.println( "!!!!!!!!!!!!!!!!!!"+user.getEmail());//выводим новый емайл

        ValidatableResponse changeUser = client.changeUser(token, user);// посылаем  запрос  с токеном и телом измененного пользователя
        ValidatableResponse creatResponse1 = client.getInfoUser(token);// посылаем  запрос гет
        check.userChangeOk(creatResponse1);// проверям что тело ответа 200   success= true
    }

    @DisplayName("Проверка изменения данных пользователя Name ответт 200 и success= true")
    @Test
    public void userChangeNameOK() {
        //Проверка создание пользователя ответт 200  success= true
        ValidatableResponse creatResponse = client.create(user);//передаем данные пользователя на API создаём пользователя
        token = check.authorizationToken(creatResponse);// сохраням токен пользователя
        user.setName(generator.randowName());//меняем пользователю новое имя

        // System.out.println( "!!!!!!!!!!!!!!!!!!"+user.getEmail());//выводим новый Name

        ValidatableResponse changeUser = client.changeUser(token, user);// посылаем  запрос  с токеном и телом измененного пользователя
        ValidatableResponse creatResponse1 = client.getInfoUser(token);// посылаем  запрос гет
        check.userChangeOk(creatResponse1);// проверям что тело ответа 200   success= true
    }

    @DisplayName("Проверка изменения данных пользова без авторизации тело ответ 401 сообщение  \"You should be authorised\"")
    @Test
    public void userChangeNameUnauthorized() {
        //Проверка создание пользователя ответт 200  success= true
        ValidatableResponse creatResponse = client.create(user);//передаем данные пользователя на API создаём пользователя
        token = check.authorizationToken(creatResponse);// сохраням токен пользователя
        user.setName(generator.randowName());//меняем пользователю новое имя

        ValidatableResponse changeUser = client.changeUserWithOutAutoriz( user);// посылаем  запрос  с токеном и телом измененного пользователя

        String message = check.userChangeUnauthorized(changeUser);// проверям что тело ответа 401 message= false
        assertEquals("You should be authorised", message); //Проверяем ответ 401 сообщение  "You should be authorised"

    }

    @DisplayName("Проверка изменения данных пользова без авторизации  тело ответ 401 сообщение  \"You should be authorised\"")
    @Test
    public void userChangeEmailUnauthorized() {
        //Проверка создание пользователя ответт 200  success= true
        ValidatableResponse creatResponse = client.create(user);//передаем данные пользователя на API создаём пользователя
        token = check.authorizationToken(creatResponse);// сохраням токен пользователя
        user.setEmail(generator.randowEmail());//меняем пользователю новое email

        ValidatableResponse changeUser = client.changeUserWithOutAutoriz( user);// посылаем  запрос  с токеном и телом измененного пользователя

        String message = check.userChangeUnauthorized(changeUser);// проверям что тело ответа 401 message= false
        assertEquals("You should be authorised", message); //Проверяем ответ 401 сообщение  "You should be authorised"

    }
}
