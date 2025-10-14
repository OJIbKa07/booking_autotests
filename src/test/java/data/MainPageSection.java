package data;

public enum MainPageSection {
    OFFERS("Offers", 2),
    TRENDING_DESTINATIONS("Trending destinations", 5),
    BROWSE_BY_PROPERTY_TYPE("Browse by property type", 4),
    TOP_UNIQUE_PROPERTIES("Stay at our top unique properties", 4),
    HOMES_GUESTS_LOVE("Homes guests love", 4);

    private final String sectionName;
    private final int expectedBlocks;

    MainPageSection(String sectionName, int expectedBlocks) {
        this.sectionName = sectionName;
        this.expectedBlocks = expectedBlocks;
    }

    public String getSectionName() {
        return sectionName;
    }

    public int getExpectedBlocks() {
        return expectedBlocks;
    }
}
