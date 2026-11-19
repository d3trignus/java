package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

// Во всех вопросительных предложениях найти и напечатать без повторений слова заданной длины.

public class Lab09 {
    private static final Logger logger = LogManager.getLogger(Lab09.class);

    public static List<Paragraph> parse(String text) {
        if (text == null) {
            logger.error("Текст равен null");
            throw new IllegalArgumentException("text не должен быть null");
        }
        List<Paragraph> paragraphs = new ArrayList<>();
        for (String line : splitLines(normalizeSpaces(text))) {
            paragraphs.add(new Paragraph(line));
        }
        logger.debug("Абзацев: {}", paragraphs.size());
        return paragraphs;
    }

    static String normalizeSpaces(String text) {
        StringBuilder sb = new StringBuilder();
        boolean previousSpace = false;
        for (char c : text.toCharArray()) {
            if (c == ' ' || c == '\t') {
                if (!previousSpace) {
                    sb.append(' ');
                }
                previousSpace = true;
            } else {
                sb.append(c);
                previousSpace = false;
            }
        }
        return sb.toString();
    }

    static List<String> splitLines(String text) {
        List<String> lines = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == '\n' || c == '\r') {
                addLine(lines, current);
            } else {
                current.append(c);
            }
        }
        addLine(lines, current);
        return lines;
    }

    private static void addLine(List<String> lines, StringBuilder current) {
        String line = current.toString().trim();
        if (!line.isEmpty()) {
            lines.add(line);
        }
        current.setLength(0);
    }

    public static Set<String> findWords(List<Paragraph> paragraphs, int length) {
        if (length < 1) {
            logger.error("Недопустимая длина слова: {}", length);
            throw new IllegalArgumentException("length должна быть >= 1");
        }
        Set<String> result = new LinkedHashSet<>();
        int questions = 0;
        for (Paragraph p : paragraphs) {
            for (Sentence s : p.getSentences()) {
                if (s.isQuestion()) {
                    questions++;
                    for (Word w : s.getWords()) {
                        if (w.length() == length) {
                            result.add(w.getValue().toLowerCase());
                        }
                    }
                }
            }
        }
        if (questions == 0) {
            logger.warn("В тексте нет вопросительных предложений");
        }
        logger.debug("Вопросительных предложений: {}, слов найдено: {}", questions, result.size());
        return result;
    }

    public static void run(Path input, Path output, int length) throws IOException {
        logger.info("Чтение файла: {}", input);
        String text;
        try {
            text = Files.readString(input, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Не удалось прочитать {}: {}", input, e.getMessage());
            throw e;
        }

        Set<String> words = findWords(parse(text), length);
        String result = words.isEmpty()
                ? "Слов длины " + length + " в вопросительных предложениях нет"
                : "Слова длины " + length + " в вопросительных предложениях: " + String.join(", ", words);

        Files.writeString(output, result + System.lineSeparator(), StandardCharsets.UTF_8);
        logger.info("Результат записан в {}: {}", output, result);
    }

    public static void main(String[] args) {
        Path input = Path.of(args.length > 0 ? args[0] : "input.txt");
        Path output = Path.of(args.length > 1 ? args[1] : "output.txt");
        int length = args.length > 2 ? Integer.parseInt(args[2]) : 4;
        try {
            run(input, output, length);
        } catch (IOException e) {
            logger.error("Ошибка ввода-вывода", e);
        }
    }
}