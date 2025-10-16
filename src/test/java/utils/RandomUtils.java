package utils;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.List;

public class RandomUtils {

    Faker faker = new Faker(new Locale("en"));

    private static final List<String> KNOWN_CITIES = List.of(
            "New York", "London", "Paris", "Tokyo", "Los Angeles",
            "Berlin", "Toronto", "Chicago", "Madrid", "Rome",
            "Sydney", "Melbourne", "San Francisco", "Barcelona", "Moscow",
            "Singapore", "Dubai", "Amsterdam", "Hong Kong", "Seoul",
            "Vienna", "Munich", "Stockholm", "Copenhagen", "Oslo",
            "Helsinki", "Prague", "Budapest", "Warsaw", "Dublin"
    );

    public String getCity() {
        return faker.options().option(KNOWN_CITIES.toArray(new String[0]));
    }

    public String getAge() {
        int age = faker.number().numberBetween(0, 18);
        return age + (age == 1 ? " year old" : " years old");
    }

    public int getCountPassengers() {
        return faker.number().numberBetween(1, 11);
    }
}
