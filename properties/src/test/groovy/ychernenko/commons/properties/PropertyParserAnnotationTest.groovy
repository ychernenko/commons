package ychernenko.commons.properties

import spock.lang.Specification
import ychernenko.commons.core.transform.parse.ParseException
import ychernenko.commons.core.transform.parse.Parser
import ychernenko.commons.properties.annotation.PropertyParser
import ychernenko.commons.properties.source.MapPropertiesSource


class PropertyParserAnnotationTest extends Specification
{
    def factory = new DefaultConfigurablePropertiesFactory()

    def "PropertyParser specifies parser for given property"() {
        given:
            def props = [
                    'a': '123',
                    'b': 'value_b'
            ]
        when:
            def parsedProps = factory.create(TestConfig, new MapPropertiesSource(props))
        then:
            parsedProps.a() == 123
            parsedProps.b() == props['b']
    }


    def "PropertyParseException is thrown for an invalid value"() {
        given:
            def props = [
                    'a': 'wrong integer',
                    'b': 'value_b'
            ]
        when:
            factory.create(TestConfig, new MapPropertiesSource(props))
        then:
            thrown(PropertyParseException)
    }



    interface TestConfig
    {
        @PropertyParser(TestParser)
        int a()

        String b()
    }
}

class TestParser implements Parser<Integer>
{

    @Override
    Integer parse(String value) throws ParseException
    {
        return Integer.parseInt(value)
    }
}