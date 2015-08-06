package ychernenko.commons.properties

import spock.lang.Specification
import ychernenko.commons.properties.annotation.Builder
import ychernenko.commons.properties.build.PropertyBuilder
import ychernenko.commons.properties.description.DefaultPropertyDescriptor
import ychernenko.commons.properties.source.MapPropertiesSource
import ychernenko.commons.properties.source.PropertiesSource


class BuilderAnnotationTest extends Specification
{

    def factory = new DefaultConfigurablePropertiesFactory()

    def "Builder annotation specifies a PropertyBuilder for given property"() {
        given:
            def props = [
                    'a': 'value a',
                    'b': 'value b',
                    'c': 'value c'
            ]
        when:
            def parsedProps = factory.create(TestConfig, new MapPropertiesSource(props))
        then:
            parsedProps.a() == props['a'] + props['c']
            parsedProps.b() == props['b']
    }


    interface TestConfig
    {
        @Builder(TestPropertyBuilder)
        String a()

        String b()
    }
}

class TestPropertyBuilder implements PropertyBuilder
{

    @Override
    Object build(DefaultPropertyDescriptor descriptor, PropertiesSource source)
    {
        return source.getPropertyValue(descriptor.key) + source.getPropertyValue('c')
    }
}