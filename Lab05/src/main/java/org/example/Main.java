// 4. Создать класс Зачетная Книжка с внутренним классом,
// с помощью объектов которого можно хранить информацию о сессиях, зачетах, экзаменах.

package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        RecordBook book = new RecordBook(2522158, "Филёнчик Павел Сергеевич");

        RecordBook.Session session1 = book.new Session(1);

        session1.addRecord("Математический анализ", "экзамен", 7);
        session1.addRecord("Алгебра и теория чисел", "экзамен", 8);
        session1.addRecord("Геометрия", "экзамен", 6);
        session1.addRecord("Методы программирования", "экзамен", 9);
        session1.addRecord("Веб-дизайн", "экзамен", 8);
        session1.addRecord("Основы права", "зачет", 9);

        book.addSession(session1);

        book.printBookLog();

        session1.sortRecordsByGrade();
        logger.info("Сортировка по оценке:");
        book.printBookLog();
    }
}