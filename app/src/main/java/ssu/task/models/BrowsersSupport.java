package ssu.task.models;

public enum BrowsersSupport {
    FIREFOX("firefox"),
    CHROME("chrome");

    private final String browserName;

    BrowsersSupport(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }
}

