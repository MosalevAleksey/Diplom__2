package UserTest;

import User.User;
import User.UserGenerator;
import User.UserStep;
import User.UserAssertion;
import User.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginUserTest {
    private final UserGenerator generator = new UserGenerator();
    private final UserStep client = new UserStep();
    private final UserAssertion check = new UserAssertion();

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

    @DisplayName("Проверка login пользователя ответт 200 и success= true")
    @Test
    public void userLoginOK() {
        //Проверка создание пользователя ответт 200  success= true

        Credentials creds = Credentials.from(user);//
        ValidatableResponse logintResponse1 = client.login(creds);
        check.loggedInSuccessfully(logintResponse1);
        //  assertEquals("true" , message);// success= true
    }

    @DisplayName("Проверка login пользователя отсутствует password message email or password are incorrect")
    @Test
    public void logginFaildWithoutPassword() {
        //Проверка создание пользователя ответт 201 и id !=0

        user.setPassword(null);
        Credentials creds = Credentials.from(user);
        ValidatableResponse logintResponse1 = client.login(creds);
        String message = check.loggedInFale(logintResponse1); // message email or password are incorrect
    }

    @DisplayName("Проверка login пользователя отсутствует password 401 и success= true")
    @Test
    public void logginFaildWithoutEmail() {
        //Проверка создание пользователя ответт 201 и id !=0

        user.setEmail(null);
        Credentials creds = Credentials.from(user);
        ValidatableResponse logintResponse1 = client.login(creds);
        String message = check.loggedInFale(logintResponse1); // message email or password are incorrect
    }

}

