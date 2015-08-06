package ychernenko.commons.properties

import spock.lang.Specification
import ychernenko.commons.core.Getter
import ychernenko.commons.properties.annotation.DefaultValueGetter
import ychernenko.commons.properties.source.MapPropertiesSource


class DefaultValueGetterAnnotationTest extends Specification
{
    def factory = new DefaultConfigurablePropertiesFactory()

    def "DefaultValue annotation specifies a default value for missing property" () {
        given:
            def props = [
                    'a': 'value_a',
            ]
        when:
            def parsedProps = factory.create(TestConfig, new MapPropertiesSource(props))
        then:
            parsedProps.a() == props['a']
            parsedProps.b() == 'default value from getter'
    }


    interface TestConfig
    {
        String a()

        @DefaultValueGetter(TestGetter)
        String b()
    }

}

class TestGetter implements Getter<String> {

    @Override
    String get()
    {
        return 'default value from getter'
    }
}
