package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.practicum.tasktracker.model.Subtask;
import ru.practicum.tasktracker.model.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Сабтаск")
class SubtaskTest {

    @Test
    @DisplayName("Должен совпадать со своей копией")
    void shouldEqualsWithCopy() {
        Subtask subtask = new Subtask("name1", "desc", TaskStatus.NEW, null );
        Subtask subtaskExpected = new Subtask("name1", "desc", TaskStatus.NEW, null);
        assertEqualsTask(subtask, subtaskExpected, "Сабтаски должны совпадать");
    }

    private static void assertEqualsTask(Subtask expected, Subtask actual, String message) {
        assertEquals(expected.getId(), actual.getId(), "id");
        assertEquals(expected.getName(), actual.getName(), "name");
        assertEquals(expected.getDescription(), actual.getDescription(), "description");
        assertEquals(expected.getStatus(), actual.getStatus(), "status");
        assertEquals(expected.getEpic(), actual.getEpic(), "epic");
    }
}