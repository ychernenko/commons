package ychernenko.commons.properties

import spock.lang.Specification
import ychernenko.commons.properties.annotation.Key
import ychernenko.commons.properties.source.MapPropertiesSource

class KeyAnnotationTest extends Specification
{

    def "key annotation overrides property name"()
    {
        given:
            def factory = new DefaultConfigurablePropertiesFactory()
            def props = [
                'key a': 'value_a',
                'key b': 'value_b'
            ]
        when:
            def parsedProps = factory.create(TestConfig, new MapPropertiesSource(props))
        then:
            parsedProps.a() == props['key a']
            parsedProps.b() == props['key b']
    }


    interface TestConfig
    {
        @Key('key a')
        String a()

        @Key('key b')
        String b()
    }
}
