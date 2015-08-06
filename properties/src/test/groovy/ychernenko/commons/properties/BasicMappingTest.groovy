package ychernenko.commons.properties

import spock.lang.Specification
import ychernenko.commons.properties.source.MapPropertiesSource


class BasicMappingTest extends Specification
{

    def factory = new DefaultConfigurablePropertiesFactory()

    def "method name is a property name"() {
        given:
            def props = [
                    'a': 'value_a',
                    'b': 'value_b'
            ]
        when:
            def parsedProps = factory.create(TestConfig, new MapPropertiesSource(props))
        then:
            parsedProps.a() == props['a']
            parsedProps.b() == props['b']
    }


    def "null is returned for missing properties"() {
        given:
            def props = [
                    'a': 'value a'
            ]
        when:
            def parsedProps = factory.create(TestConfig, new MapPropertiesSource(props))
        then:
            parsedProps.a() == props['a']
            parsedProps.b() == null
    }


    interface TestConfig
    {
        String a()
        String b()
    }
}