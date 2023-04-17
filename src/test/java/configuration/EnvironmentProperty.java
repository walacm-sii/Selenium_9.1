package configuration;

import configuration.domain.Configuration;
import configuration.domain.Environment;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class EnvironmentProperty {

    private Map<String, String> properties = new HashMap<>();
    private static EnvironmentProperty environmentPropertyInstance;


    private EnvironmentProperty() {
        importProperties();
        log.info("Configuration loaded to EnvironmentProperty");
        setSystemProperties();
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public static EnvironmentProperty getInstance() {
        if (environmentPropertyInstance == null) {
            environmentPropertyInstance = new EnvironmentProperty();
        }
        return environmentPropertyInstance;
    }

    private void setSystemProperties() {
        properties.forEach(System::setProperty);
        log.info("Configuration saved as system properties: " + properties);
    }

    @SneakyThrows
    private void importProperties() {
        Configuration configuration = YamlParser.getConfiguration();
        Environment environment = getSpecifiedEnvironment(configuration.getEnvironments(), configuration.getDefaultEnvironment());
        properties.putAll(configuration.getBasicConfiguration());
        properties.putAll(environment.getEnvironment());
    }

    private Environment getSpecifiedEnvironment(List<Environment> environmentList, String defaultBrowser) {
        Environment environment;
        environment = environmentList.stream()
                .filter(env -> env.getEnvironment().get("env name").equals(defaultBrowser))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple environments matching default environment name: \""
                            + defaultBrowser + "\"");
                }).orElse(null);
        log.info("Default configuration found for \"{}\" environment", defaultBrowser);
        return environment;
    }

}
