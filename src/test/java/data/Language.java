package data;

public enum Language {
    RU("Русский", "Бронируйте именно то,"),
    UK("English (UK)", "Find exactly what");


    public final String country;
    public final String description;

    Language(String country, String description) {
        this.country = country;
        this.description = description;
    }


}