package configuration.domain;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private Map<String, String> environment = new HashMap<>();

    @JsonAnySetter
    void setEnvironment(String key, String val) {
        environment.put(key, val);
    }

    public Map<String, String> getEnvironment() {
        return environment;
    }
}

