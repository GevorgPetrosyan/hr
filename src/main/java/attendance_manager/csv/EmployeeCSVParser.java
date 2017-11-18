package attendance_manager.csv;

import attendance_manager.model.EmployeeAttendanceDto;
import attendance_manager.model.EmployeeCSVDto;
import attendance_manager.model.EmployeeIdeintiferDto;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeCSVParser {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static Map<EmployeeIdeintiferDto, List<EmployeeAttendanceDto>> generate(final String name) {
        final String cvsSplitBy = ",";
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final InputStream is = classloader.getResourceAsStream(name);
        final List<EmployeeCSVDto> employeeDtos = new ArrayList<>();
        Scanner scanner = new Scanner(is);
        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());
            employeeDtos.add(convertToDto(line));
        }
        return parseMap(employeeDtos);
    }

    private static EmployeeCSVDto convertToDto(final List<String> data) {
        final EmployeeCSVDto employeeCSVDto = new EmployeeCSVDto();
        employeeCSVDto.setName(data.get(0));
        employeeCSVDto.setReason(data.get(1));
        employeeCSVDto.setStart(data.get(2));
        employeeCSVDto.setEnd(data.get(3));
        employeeCSVDto.setDisposed(data.get(4));
        employeeCSVDto.setJoined(data.get(5));
        employeeCSVDto.setLeaved(data.get(6));
        employeeCSVDto.setUsername(data.get(7));
        employeeCSVDto.setPassword(data.get(8));
        return employeeCSVDto;
    }

    private static Map<EmployeeIdeintiferDto, List<EmployeeAttendanceDto>> parseMap(final List<EmployeeCSVDto> csvDtos) {
        final Map<EmployeeIdeintiferDto, List<EmployeeAttendanceDto>> data = new HashMap<>();
        final Optional<EmployeeIdeintiferDto> employeeIdeintiferDtoOptional = csvDtos.stream().filter(csvDto -> StringUtils.isNotBlank(csvDto.getName())).findFirst().map(csvDto -> {
            final EmployeeIdeintiferDto temporaryEmployeeIdeintiferDto = new EmployeeIdeintiferDto();
            temporaryEmployeeIdeintiferDto.setName(csvDto.getName());
            temporaryEmployeeIdeintiferDto.setJoined(csvDto.getJoined());
            temporaryEmployeeIdeintiferDto.setLeaved(csvDto.getLeaved());
            temporaryEmployeeIdeintiferDto.setUsername(csvDto.getUsername());
            temporaryEmployeeIdeintiferDto.setPassword(csvDto.getPassword());
            return temporaryEmployeeIdeintiferDto;
        });
        List<EmployeeAttendanceDto> attendanceDtos = csvDtos.stream().filter(csvDto -> StringUtils.isNotBlank(csvDto.getReason())).map(csvDto -> {
            final EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();
            employeeAttendanceDto.setDisposed(csvDto.getDisposed());
            employeeAttendanceDto.setEnd(csvDto.getEnd());
            employeeAttendanceDto.setReason(csvDto.getReason());
            employeeAttendanceDto.setStart(csvDto.getStart());
            return employeeAttendanceDto;
        }).collect(Collectors.toList());
        employeeIdeintiferDtoOptional.ifPresent(employeeIdeintiferDto -> data.put(employeeIdeintiferDto, attendanceDtos));
        return data;
    }


    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }


}
