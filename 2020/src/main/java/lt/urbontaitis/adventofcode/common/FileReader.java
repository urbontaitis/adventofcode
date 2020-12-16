package lt.urbontaitis.adventofcode.common;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
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

    public static List<String> readFileAndSplitByNewLine(String subPath)
        throws IOException, URISyntaxException {
        URI uri = FileReader.class.getResource("/" + subPath).toURI();
        return Arrays.asList(Files.readString(Paths.get(uri)).split("\\n\\n"));
    }
}
