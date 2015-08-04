package ychernenko.commons.properties.build;

import java.util.HashMap;
import java.util.Map;

import ychernenko.commons.properties.description.DefaultPropertyDescriptor;
import ychernenko.commons.properties.source.PropertiesSource;

public class GroupPropertyBuilder implements PropertyBuilder<Map<String, String>> {

    private final String prefix;

    public GroupPropertyBuilder(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Map<String, String> load(DefaultPropertyDescriptor<Map<String, String>> descriptor, PropertiesSource source) {
        Map<String, String> map = new HashMap<>();
        for (String name : source.getPropertyNames()) {
            if (name.startsWith(prefix)) {
                String value = source.getPropertyValue(name);
                String prefixFreeName = name.substring(prefix.length());
                map.put(prefixFreeName, value);
            }
        }
        return map;
    }
}
