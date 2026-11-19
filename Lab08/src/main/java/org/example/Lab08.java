package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lab08 {
    private static final Logger logger = LogManager.getLogger(Lab08.class);

    private static final String CONSONANTS = "бвгджзйклмнпрстфхцчшщbcdfghjklmnpqrstvwxyz";
    private static final String NON_LETTERS = "[^\\p{L}]+";
    private static final String SENTENCE_SPLIT = "(?<=[.!?])\\s+";

    // A4
    // В тексте после k-го символа вставить заданную подстроку.

    // Способ 1
    public static String insertAfterKString(String text, int k, String sub) {
        checkInsertArgs(text, k, sub);
        if (k > text.length()) {
            logger.warn("k={} больше длины текста ({}), текст не изменён", k, text.length());
            return text;
        }
        String result = text.substring(0, k) + sub + text.substring(k);
        logger.debug("A4 (String): '{}' -> '{}'", text, result);
        return result;
    }

    // Способ 2
    public static String insertAfterKStream(String text, int k, String sub) {
        checkInsertArgs(text, k, sub);
        if (k > text.length()) {
            logger.warn("k={} больше длины текста ({}), текст не изменён", k, text.length());
            return text;
        }
        String result = Stream.of(text.substring(0, k), sub, text.substring(k))
                .collect(Collectors.joining());
        logger.debug("A4 (Stream): '{}' -> '{}'", text, result);
        return result;
    }

    private static void checkInsertArgs(String text, int k, String sub) {
        if (text == null || sub == null) {
            logger.error("Текст или подстрока равны null");
            throw new IllegalArgumentException("text и sub не должны быть null");
        }
        if (k < 1) {
            logger.error("Недопустимое k={}", k);
            throw new IllegalArgumentException("k должно быть >= 1");
        }
    }

    // A18
    // В тексте определить все согласные буквы, встречающиеся не более чем в двух словах.

    // Способ 1
    public static String findConsonantsString(String text) {
        if (text == null) {
            logger.error("Текст равен null");
            throw new IllegalArgumentException("text не должен быть null");
        }

        String[] words = text.toLowerCase().split(NON_LETTERS);
        String result = "";

        for (int i = 0; i < CONSONANTS.length(); i++) {
            char c = CONSONANTS.charAt(i);
            int count = 0;

            for (String word : words) {
                if (word.indexOf(c) != -1) {
                    count++;
                    if (count > 2) break;
                }
            }

            if (count == 1 || count == 2) {
                result += c + " ";
            }
        }

        result = result.trim();
        logger.debug("A18 (String): '{}'", result);
        return result;
    }

    // Способ 2
    public static String findConsonantsStream(String text) {
        if (text == null) {
            logger.error("Текст равен null");
            throw new IllegalArgumentException("text не должен быть null");
        }

        String[] words = text.toLowerCase().split(NON_LETTERS);

        String result = CONSONANTS.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .filter(c -> {
                    long count = Arrays.stream(words).filter(w -> w.contains(c)).count();
                    return count == 1 || count == 2;
                })
                .collect(Collectors.joining(" "));

        logger.debug("A18 (Stream): '{}'", result);
        return result;
    }

    // C4
    // Вычеркнуть из текста минимальное количество предложений так,
    // чтобы у любых двух оставшихся предложений было хотя бы одно общее слово.

    // Способ 1
    public static String removeSentencesString(String text) {
        if (text == null) {
            logger.error("Текст равен null");
            throw new IllegalArgumentException("text не должен быть null");
        }
        if (text.trim().isEmpty()) {
            return "";
        }

        String[] sentences = text.trim().split(SENTENCE_SPLIT);
        int n = sentences.length;

        String[][] wordsBySentence = new String[n][];
        for (int i = 0; i < n; i++) {
            wordsBySentence[i] = sentences[i].toLowerCase().split(NON_LETTERS);
        }

        String bestWord = "";
        int maxSentences = 0;

        for (int i = 0; i < n; i++) {
            for (String word : wordsBySentence[i]) {
                if (word.isEmpty()) continue;

                int count = 0;
                for (int j = 0; j < n; j++) {
                    if (containsWord(wordsBySentence[j], word)) {
                        count++;
                    }
                }
                if (count > maxSentences) {
                    maxSentences = count;
                    bestWord = word;
                }
            }
        }

        String result = "";
        int kept = 0;
        for (int i = 0; i < n; i++) {
            boolean keep = (maxSentences <= 1) ? (i == 0) : containsWord(wordsBySentence[i], bestWord);
            if (keep) {
                result = result.isEmpty() ? sentences[i] : result + " " + sentences[i];
                kept++;
            }
        }

        logger.debug("C4 (String): оставлено {} из {} предложений", kept, n);
        return result;
    }

    private static boolean containsWord(String[] words, String target) {
        for (String w : words) {
            if (w.equals(target)) {
                return true;
            }
        }
        return false;
    }

    // Способ 2
    public static String removeSentencesStream(String text) {
        if (text == null) {
            logger.error("Текст равен null");
            throw new IllegalArgumentException("text не должен быть null");
        }
        if (text.trim().isEmpty()) {
            return "";
        }

        List<String> sentences = Arrays.asList(text.trim().split(SENTENCE_SPLIT));

        List<Set<String>> wordSets = sentences.stream()
                .map(s -> Arrays.stream(s.toLowerCase().split(NON_LETTERS))
                        .filter(w -> !w.isEmpty())
                        .collect(Collectors.toSet()))
                .collect(Collectors.toList());

        String bestWord = wordSets.stream()
                .flatMap(Set::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        String result = (bestWord == null)
                ? sentences.get(0)
                : sentences.stream()
                .filter(s -> Arrays.asList(s.toLowerCase().split(NON_LETTERS)).contains(bestWord))
                .collect(Collectors.joining(" "));

        logger.debug("C4 (Stream): результат готов (опорное слово: {})", bestWord);
        return result;
    }
}
