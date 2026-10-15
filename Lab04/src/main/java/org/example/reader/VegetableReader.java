package org.example.reader;

import org.example.entity.Vegetable;
import org.example.factory.VegetableFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class VegetableReader {
    private static final Logger logger = LogManager.getLogger(VegetableReader.class);

    private final VegetableFactory factory = new VegetableFactory();

    public Vegetable[] readFromResources(String fileName) {
        logger.info("Чтение из {}", fileName);

        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            logger.error("Файл не найден: {}", fileName);
            throw new IllegalArgumentException("Файл не найден: " + fileName);
        }

        try (Scanner scanner = new Scanner(is, StandardCharsets.UTF_8)) {
            Vegetable[] result = scanner.useDelimiter("\\R")
                    .tokens()
                    .map(String::strip)
                    .filter(s -> {
                        if (s.isEmpty() || s.startsWith("#")) {
                            logger.debug("Пропущена строка (пустая/комментарий): '{}'", s);
                            return false;
                        }
                        return true;
                    })
                    .map(this::parse)
                    .filter(Objects::nonNull)
                    .toArray(Vegetable[]::new);

            logger.info("Прочитано овощей: {}", result.length);
            return result;
        } catch (RuntimeException e) {
            logger.error("Ошибка при чтении файла: {}", fileName, e);
            throw e;
        }
    }

    private Vegetable parse(String line) {
        try {
            Vegetable vegetable = factory.createVegetable(line.split(";"));
            logger.debug("Успешно прочитан овощ: {}", vegetable.getName());
            return vegetable;
        } catch (RuntimeException e) {
            logger.warn("Пропущена строка: '{}' ({})", line, e.getMessage());
            return null;
        }
    }
}