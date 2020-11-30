package lt.urbontaitis.adventofcode.common;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    private FileReader() {}

    public static String readFile(String fileName) throws IOException {
        final InputStream stream = FileReader.class.getResourceAsStream("/" + fileName);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            return bf.lines().collect(Collectors.joining(","));
        }
    }

    public static List<String> readFileToList(String fileName) throws IOException {
        final InputStream stream = FileReader.class.getResourceAsStream("/" + fileName);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            return bf.lines().collect(toList());
        }
    }
}
