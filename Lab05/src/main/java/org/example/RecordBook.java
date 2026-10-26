package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecordBook {
    private static final Logger logger = LogManager.getLogger(RecordBook.class);

    private int number;
    private String studentName;
    private List<Session> sessions;

    public RecordBook(int number, String studentName) {
        this.number = number;
        this.studentName = studentName;
        this.sessions = new ArrayList<>();

        logger.info("Создана зачетная книжка №{} для студента {}", number, studentName);
    }

    public void addSession(Session session) {
        if (session != null) {
            this.sessions.add(session);
            logger.info("В зачетную книжку добавлена сессия №{}", session.number);
        } else {
            logger.warn("Попытка добавить null в список сессий");
        }
    }

    public class Session {
        private int number;
        private List<SubjectRecord> records;

        public Session(int number) {
            this.number = number;
            this.records = new ArrayList<>();

            logger.info("Инициализирована новая сессия №{}", number);
        }

        public void addRecord(String subject, String type, int grade) {
            SubjectRecord record = new SubjectRecord(subject, type, grade);
            records.add(record);

            logger.info("В сессию №{} добавлена запись: {}", number, record);
        }

        public List<SubjectRecord> getRecords() {
            return records;
        }

        public void sortRecordsByGrade() {
            logger.info("Выполняется сортировка записей сессии № {} по оценке", number);

            Collections.sort(records, new Comparator<SubjectRecord>() {
                @Override
                public int compare(SubjectRecord o1, SubjectRecord o2) {
                    return Integer.compare(o2.grade, o1.grade);
                }
            });
        }

        public void printSessionLog() {
            logger.info("Вывод данных сессии № {} для студента {}", number, studentName);
            for (SubjectRecord record : records) {
                logger.info(record);
            }
        }

        public class SubjectRecord {
            private String name;
            private String type;
            private int grade;

            public SubjectRecord(String name, String type, int grade) {
                this.name = name;
                this.type = type;
                this.grade = grade;
            }

            public int getGrade() {
                return this.grade;
            }

            public String getSubjectName() {
                return this.name;
            }

            @Override
            public String toString() {
                return String.format("Тип: %s, Предмет: %s, Оценка: %d", type, name, grade);
            }
        }
    }

    public void printBookLog() {
        logger.info("=== Зачетная книжка № {}, студент: {} ===", number, studentName);
        for (Session session : sessions) {
            session.printSessionLog();
        }
        logger.info("================================================");
    }
}
