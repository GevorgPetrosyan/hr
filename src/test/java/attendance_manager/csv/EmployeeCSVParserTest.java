package attendance_manager.csv;

import org.junit.Test;

import java.io.InputStream;

public class EmployeeCSVParserTest {

    @Test
    public void testParse() {
        EmployeeCSVParser.generate("test.csv");
    }

}

