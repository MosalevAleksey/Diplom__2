package UserTest;


import User.User;
import User.UserAssertion;
import User.UserGenerator;
import User.UserStep;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateUserTest {
    private final UserGenerator generator = new UserGenerator();
    private final UserStep client = new UserStep();
    private final UserAssertion check = new UserAssertion();

    private String token;
    private User user;

    @Before
    public void createUser() {
        user = generator.randow();//создаем объект с полями юзера
    }


    @DisplayName("Проверка создание пользователя ответт 201 и success= true")
    @Test
    public void userCreataOK() {
        //Проверка создание пользователя ответт 201 и id !=0

        ValidatableResponse creatResponse = client.create(user);//передаем данные пользователя на API создаём пользователя
        token = check.authorizationToken(creatResponse);// сохраням токен пользователя
        check.createdSuccessfully(creatResponse);//Проверяем ответ 200 ок
        ValidatableResponse deletUser = client.DeliteUser(token, user);// удаляем пользователя
    }

    @DisplayName("Проверка создание пользователя с одинаковыми полями 403 и messsage = User already exists")
    @Test
    public void userDoNotCreataSameUser() {
        //Проверка создание пользователя ответт 403 и id !=0

        ValidatableResponse creatResponse = client.create(user);//передаем данные пользователя на API создаём пользователя
        token = check.authorizationToken(creatResponse);// сохраням токен пользователя
        check.createdSuccessfully(creatResponse);//Проверяем ответ 200 ок
        ValidatableResponse creatResponse1 = client.create(user);//передаем данные  товоже пользователя на API в отсет получаем ощибку
        String message = check.creationFaildLoginRepeated(creatResponse1);
        assertEquals("User already exists", message); //Проверяем ответ 403 сообщение  "User already exists"
        ValidatableResponse deletUser = client.DeliteUser(token, user);// удаляем пользователя

    }

    @DisplayName("Проверка создание пользователя с пустым паролем 403 и messsage = Email, password and name are required fields")
    @Test
    public void canNotCreateUserWithoutPassword() {
        //Проверка создание пользователя ответт 403 и id !=0

        user.setPassword(null);// обнуляем пароль
        ValidatableResponse creatResponse = client.create(user);//передаем данные пользователя на API создаём пользователя
        String message = check.creationFaildRequiredFields(creatResponse);
        assertEquals("Email, password and name are required fields", message); //Проверяем ответ 403 сообщение  "Email, password and name are required fields""

    }

    @DisplayName("Проверка создание пользователя с пустым email 403 и messsage = Email, password and name are required fields")
    @Test
    public void canNotCreateUserWithoutEmail() {
        //Проверка создание пользователя ответт 403 и id !=0

        user.setEmail(null);// обнуляем пароль
        ValidatableResponse creatResponse = client.create(user);//передаем данные пользователя на API создаём пользователя
        String message = check.creationFaildRequiredFields(creatResponse);
        assertEquals("Email, password and name are required fields", message); //Проверяем ответ 403 сообщение  "Email, password and name are required fields""

    }

    @DisplayName("Проверка создание пользователя с пустым Name 403 и messsage = Email, password and name are required fields")
    @Test
    public void canNotCreateUserWithoutName() {
        //Проверка создание пользователя ответт 403 и id !=0

        user.setName(null);// обнуляем пароль
        ValidatableResponse creatResponse = client.create(user);//передаем данные пользователя на API создаём пользователя
        String message = check.creationFaildRequiredFields(creatResponse);
        assertEquals("Email, password and name are required fields", message); //Проверяем ответ 403 сообщение  "Email, password and name are required fields""

    }
}
