package configuration.domain;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class BasicConfiguration {
    private Map<String, String> basicConfiguration = new HashMap<>();

    @JsonAnySetter
    void setBrowser(String key, String val) {
        basicConfiguration.put(key, val);
    }

    public Map<String, String> getBasicConfiguration() {
        return basicConfiguration;
    }
}