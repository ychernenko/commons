package ychernenko.commons.properties.source;

import java.util.Collection;
import java.util.Map;

public class MapPropertiesSource implements PropertiesSource {

    private final Map<String, String> map;

    public MapPropertiesSource(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public Collection<String> getPropertyNames() {
        return map.keySet();
    }

    @Override
    public String getPropertyValue(String name) {
        return map.get(name);
    }
}
