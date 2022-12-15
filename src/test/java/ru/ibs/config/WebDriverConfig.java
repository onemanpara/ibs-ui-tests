package ru.ibs.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${host}.properties"
})
public interface WebDriverConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserSize")
    @DefaultValue("1920x1980")
    String browserSize();


    @Key("remote")
    String remote();

}
