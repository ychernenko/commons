package ychernenko.commons.properties

import spock.lang.Specification
import ychernenko.commons.properties.annotation.DefaultValue
import ychernenko.commons.properties.source.MapPropertiesSource


class DefaultValueAnnotationTest extends Specification
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
            parsedProps.b() == 'default value for b'
    }

    interface TestConfig
    {
        @DefaultValue('default value for a')
        String a()

        @DefaultValue('default value for b')
        String b()
    }
}