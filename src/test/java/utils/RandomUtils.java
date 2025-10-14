package utils;

import com.github.javafaker.Faker;
import java.util.Locale;

public class RandomUtils {

    Faker faker = new Faker(new Locale("en"));

    public String getCity() {
        return faker.address().city();
    }

    public String getAge() {
        int age = faker.number().numberBetween(0, 18);
        return age + (age == 1 ? " year old" : " years old");
    }

    public int getCountPassengers() {
        return faker.number().numberBetween(1, 11);
    }
}
