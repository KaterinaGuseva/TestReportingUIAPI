package configurations;

import aquality.selenium.core.utilities.JsonSettingsFile;

public class TestConfiguration {

    private final String configurationFileName;

    public TestConfiguration(String configurationFileName) {
        this.configurationFileName = configurationFileName;
    }

    public String getTestConfiguration(String jsonKeyName) {
        return new JsonSettingsFile(configurationFileName).getValue(jsonKeyName).toString();
    }
}
