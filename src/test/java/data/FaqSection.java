package data;

public enum FaqSection {
    STAYS("Stays"),
    FLIGHTS("Flights"),
    CAR_RENTALS("Car rentals"),
    ATTRACTIONS("Attractions"),
    AIRPORT_TAXIS("Airport taxis"),
    INSURANCE("Insurance"),
    OTHER("Other");

    private final String sectionName;

    FaqSection(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionName() {
        return sectionName;
    }
}