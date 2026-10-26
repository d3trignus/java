import org.example.RecordBook;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class RecordBookTest {

    @Test
    public void testAddSessionAndRecords() {
        RecordBook recordBook = new RecordBook(2522159, "Иванов Иван Иванович");
        RecordBook.Session session = recordBook.new Session(1);

        session.addRecord("Математический анализ", "экзамен", 8);
        session.addRecord("Геометрия", "зачет", 5);
        recordBook.addSession(session);

        List<RecordBook.Session.SubjectRecord> records = session.getRecords();
        Assert.assertEquals(records.size(), 2, "Количество записей в сессии должно быть равно 2");
        Assert.assertEquals(records.get(0).getSubjectName(), "Математический анализ");
        Assert.assertEquals(records.get(0).getGrade(), 8);
        Assert.assertEquals(records.get(1).getSubjectName(), "Геометрия");
        Assert.assertEquals(records.get(1).getGrade(), 5);
    }

    @Test
    public void testSortRecordsByGradeWithAnonymousClass() {
        // Arrange
        RecordBook recordBook = new RecordBook(2522160, "Смирнова Анна Евгеньевна");
        RecordBook.Session session = recordBook.new Session(2);

        session.addRecord("История белорусской государственности", "экзамен", 6);
        session.addRecord("Алгебра и теория чисел", "экзамен", 10);
        session.addRecord("Алгебра и теория чисел", "зачет", 8);

        session.sortRecordsByGrade();

        List<RecordBook.Session.SubjectRecord> records = session.getRecords();
        Assert.assertEquals(records.get(0).getGrade(), 10, "Первая оценка должна быть самой высокой (10)");
        Assert.assertEquals(records.get(1).getGrade(), 8, "Вторая оценка должна быть 8");
        Assert.assertEquals(records.get(2).getGrade(), 6, "Последняя оценка должна быть самой низкой (6)");
    }
}