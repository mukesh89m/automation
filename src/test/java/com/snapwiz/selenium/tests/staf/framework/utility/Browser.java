package com.snapwiz.selenium.tests.staf.framework.utility;

/**
 * Created by Murthi on 13-01-2016.
 * This class represents browsers. For add support of your browser - add it to this enum
 */
public enum  Browser {
    FIREFOX("firefox"),
    CHROME("chrome"),
    IE("ie"),
    SAFARI("safari"),
    REMOTE("remote"),
    UNIT("unit"),
    GECKO("gecko"),
    PHANTOMJS("phantomjs");
    private String browserName;

    private Browser(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }

    /**
     * returns browser object by name
     * @param name name of browser
     * @return browser object
     */
    public static Browser getByName(String name){
        for(Browser browser : values()) {
            if(browser.getBrowserName().equalsIgnoreCase(name)) {
                return browser;
            }
        }
        return null;
    }


}
