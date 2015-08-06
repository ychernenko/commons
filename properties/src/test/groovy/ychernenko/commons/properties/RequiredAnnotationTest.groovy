package ychernenko.commons.properties

import spock.lang.Specification
import ychernenko.commons.properties.annotation.Required
import ychernenko.commons.properties.source.MapPropertiesSource


class RequiredAnnotationTest extends Specification
{
    def factory = new DefaultConfigurablePropertiesFactory()

    def "expect MissingPropertyException in case of missing required property"() {
        when:
            factory.create(TestConfig, new MapPropertiesSource([:]))
        then:
            thrown(MissingPropertyException)
    }


    def "no exceptions if property is present"() {
        given:
            def props = [
                'a': 'value_a'
            ]
        when:
            TestConfig parsedProps = factory.create(TestConfig, new MapPropertiesSource(props))
        then:
            parsedProps.a() == props['a']
    }


    interface TestConfig
    {
        @Required
        String a()
    }
}