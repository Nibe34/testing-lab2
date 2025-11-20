package edu.pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
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

        Map<String, Integer> freq = new HashMap<>(50_000);

        Charset[] encodings = {StandardCharsets.UTF_8, Charset.forName("Cp1251")};
        boolean success = false;

        for (Charset charset : encodings) {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH), charset)) {
                processFile(reader, freq);
                success = true;
                break;
            } catch (MalformedInputException e) {
                System.out.println("Failed to read with charset: " + charset + ", trying next...");
            }
        }

        if (!success) {
            throw new IOException("Cannot read file with available charsets.");
        }

        // Sort entries by frequency
        List<Map.Entry<String, Integer>> sorted =
                freq.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toList());

        System.out.println("Top 30 most frequent words:");
        for (int i = sorted.size() - 1; i >= Math.max(sorted.size() - 30, 0); i--) {
            System.out.println(sorted.get(i).getKey() + " " + sorted.get(i).getValue());
        }

        LocalDateTime finish = LocalDateTime.now();
        System.out.println("------");
        System.out.println("Execution time (ms): " +
                ChronoUnit.MILLIS.between(start, finish));
    }

    private static void processFile(BufferedReader reader, Map<String, Integer> freq) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            StringBuilder cleaned = new StringBuilder(line.length());
            for (char c : line.toCharArray()) {
                if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
                    cleaned.append(Character.toLowerCase(c));
                else
                    cleaned.append(' ');
            }
            String[] words = cleaned.toString().split("\\s+");
            for (String w : words) {
                if (!w.isEmpty()) {
                    freq.merge(w, 1, Integer::sum);
                }
            }
        }
    }
}
