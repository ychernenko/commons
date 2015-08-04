package ychernenko.commons.core.transform.parse;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathParser implements Parser<Path> {

    @Override
    public Path parse(String value) {
        try {
            return Paths.get(value);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }
}
