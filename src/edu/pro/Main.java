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

public class Main {

    private static final String FILE_PATH = "src/edu/pro/txt/harry.txt";

    public static void main(String[] args) throws IOException {

        LocalDateTime start = LocalDateTime.now();

        Map<String, Integer> freq = new HashMap<>(60_000);

        Charset[] encodings = {StandardCharsets.UTF_8, Charset.forName("Cp1251")};
        boolean success = false;

        for (Charset charset : encodings) {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH), charset)) {
                fastProcessFile(reader, freq);
                success = true;
                break;
            } catch (MalformedInputException e) {
                System.out.println("Failed charset: " + charset + ", trying next...");
            }
        }

        if (!success) {
            throw new IOException("Cannot read with available charsets.");
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort(Map.Entry.comparingByValue());

        System.out.println("Top 30:");
        for (int i = list.size() - 1; i >= Math.max(list.size() - 30, 0); i--) {
            Map.Entry<String, Integer> e = list.get(i);
            System.out.println(e.getKey() + " " + e.getValue());
        }

        LocalDateTime finish = LocalDateTime.now();
        System.out.println("------");
        System.out.println("Execution time (ms): " + ChronoUnit.MILLIS.between(start, finish));
    }



    private static void fastProcessFile(BufferedReader reader, Map<String, Integer> freq) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {

            char[] arr = line.toCharArray();
            int len = arr.length;

            StringBuilder sb = new StringBuilder(16);

            for (int i = 0; i < len; i++) {
                char c = arr[i];

                if ((c >= 'A' && c <= 'Z')) {
                    sb.append((char)(c + 32));
                } else if (c >= 'a' && c <= 'z') {
                    sb.append(c);
                } else {
                    if (sb.length() > 0) {
                        String w = sb.toString();
                        freq.merge(w, 1, Integer::sum);
                        sb.setLength(0);
                    }
                }
            }

            if (sb.length() > 0) {
                freq.merge(sb.toString(), 1, Integer::sum);
            }
        }
    }
}
