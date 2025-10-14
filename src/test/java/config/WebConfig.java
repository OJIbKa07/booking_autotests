package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:configs/${env}.properties"
})
public interface WebConfig extends Config {
    @Key("browser")
    @DefaultValue("CHROME")
    String browser();

    @Key("browserVersion")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("remoteWebDriverUrl")
    String remoteWebDriverUrl();

    @Key("baseUrl")
    @DefaultValue("https://www.booking.com")
    String baseUrl();
}