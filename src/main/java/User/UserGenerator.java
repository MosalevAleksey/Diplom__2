package User;

import com.github.javafaker.Faker;

public class UserGenerator {
    public User generic() {
        return new User("Jack@my.vily", "P@ssword", "Sparrow");
    }

    Faker faker = new Faker();

    public User randow() {

        String emamail = faker.internet().emailAddress();
        String password = faker.internet().password();
        String name = faker.name().firstName();

        return new User(emamail, password, name);
    }

    public String randowEmail() {

        String emamail = faker.internet().emailAddress();


        return emamail;
    }

    public String randowName() {

        String name = faker.name().firstName();

        return name;
    }
}
