package ru.ibs.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {

    Faker fakerRu = new Faker(new Locale("ru"));
    Faker fakerEn = new Faker(new Locale("en"));

    public String
            fullName = fakerRu.name().fullName(),
            companyName = fakerRu.company().name(),
            phone = fakerRu.phoneNumber().subscriberNumber(11),
            email = fakerEn.internet().emailAddress(),
            messageText = fakerRu.name().username(),
            invalidEmail = fakerRu.name().firstName(),
            invalidPhone = fakerRu.phoneNumber().subscriberNumber(10);


}
