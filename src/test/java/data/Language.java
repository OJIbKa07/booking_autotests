package data;

public enum Language {
    RU("Русский", "До -15% на аренду автомобилей"),
    UK("English (UK)", "Up to 15% off on rental cars");


    public final String country;
    public final String description;

    Language(String country, String description) {
        this.country = country;
        this.description = description;
    }


}