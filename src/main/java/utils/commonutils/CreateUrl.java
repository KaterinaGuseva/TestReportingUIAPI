package utils.commonutils;

import configurations.TestConfiguration;
import constants.ConfigurationConstants;

public class CreateUrl {

    private TestConfiguration testUrlConfiguration = new TestConfiguration(ConfigurationConstants.CONFIGURATION_FILE);
    private TestConfiguration testUiCredentials = new TestConfiguration(ConfigurationConstants.UI_CREDENTIALS_FILE);

    public String getConfigurationValue(String jsonKeyName) {
        return testUrlConfiguration.getTestConfiguration(jsonKeyName);
    }

    public String getUiCredentialsValue(String jsonKeyName) {
        return testUiCredentials.getTestConfiguration(jsonKeyName);
    }

    public String getApiUrl() {
        return CreateString.getString(getConfigurationValue(ConfigurationConstants.KEY_PROTOCOL),
                getConfigurationValue(ConfigurationConstants.KEY_DOMAIN_NAME),
                getConfigurationValue(ConfigurationConstants.KEY_PORT),
                getConfigurationValue(ConfigurationConstants.KEY_PATH_API));
    }

    public  String getUiUrl() {
        return CreateString.getString(getConfigurationValue(ConfigurationConstants.KEY_PROTOCOL),
                getUiCredentialsValue(ConfigurationConstants.KEY_LOGIN),
                ConfigurationConstants.URL_CREDENTIALS_MARK,
                getUiCredentialsValue(ConfigurationConstants.KEY_PASSWORD),
                ConfigurationConstants.URL_MARK,
                getConfigurationValue(ConfigurationConstants.KEY_DOMAIN_NAME),
                getConfigurationValue(ConfigurationConstants.KEY_PORT),
                getConfigurationValue(ConfigurationConstants.KEY_PATH_UI));
    }
}
