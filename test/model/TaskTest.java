package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.practicum.tasktracker.model.Task;
import ru.practicum.tasktracker.model.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Таск")
class TaskTest {

    @Test
    @DisplayName("Должен совпадать со своей копией")
    void shouldEqualsWithCopy() {
        Task task = new Task("name1", "desc", TaskStatus.NEW);
        Task taskExpected = new Task("name1", "desc", TaskStatus.NEW);
        assertEqualsTask(task, taskExpected, "Таски должны совпадать");
    }

    private static void assertEqualsTask(Task expected, Task actual, String message) {
        assertEquals(expected.getId(), actual.getId(), "id");
        assertEquals(expected.getName(), actual.getName(), "name");
        assertEquals(expected.getDescription(), actual.getDescription(), "description");
        assertEquals(expected.getStatus(), actual.getStatus(), "status");
    }
}