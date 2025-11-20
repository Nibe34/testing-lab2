package edu.pro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String FILE_PATH = "src/edu/pro/txt/harry.txt";

    public static void main(String[] args) throws IOException {

        LocalDateTime start = LocalDateTime.now();

        String content = loadAndClean(FILE_PATH);

        // Split words
        String[] words = content.split("\\s+");

        // Count frequencies using Map
        Map<String, Long> frequency = Arrays.stream(words)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        // Sort by frequency
        List<Map.Entry<String, Long>> sorted = frequency.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        // Print top 30 most frequent
        for (int i = sorted.size() - 1; i >= sorted.size() - 30; i--) {
            System.out.println(sorted.get(i).getKey() + " " + sorted.get(i).getValue());
        }

        LocalDateTime finish = LocalDateTime.now();

        System.out.println("------");
        System.out.println(ChronoUnit.MILLIS.between(start, finish));
    }

    private static String loadAndClean(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)))
                .replaceAll("[^A-Za-z ]", " ")
                .toLowerCase(Locale.ROOT);
    }
}
