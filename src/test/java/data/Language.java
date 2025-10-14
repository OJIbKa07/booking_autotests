package data;

public enum Language {
    RU("Русский", "Найдите жилье для новой поездки"),
    UK("English (UK)", "Find your next stay");


    public final String country;
    public final String description;

    Language(String country, String description) {
        this.country = country;
        this.description = description;
    }


}