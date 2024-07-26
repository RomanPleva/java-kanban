package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.practicum.tasktracker.model.Epic;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Эпик")
class EpicTest {

    @Test
    @DisplayName("Должен совпадать со своей копией")
    void shouldEqualsWithCopy() {
        Epic epic = new Epic("name1", "desc");
        Epic epicExpected = new Epic("name1", "desc");
        assertEqualsTask(epic, epicExpected, "Эпики должны совпадать");
    }

    private static void assertEqualsTask(Epic expected, Epic actual, String message) {
        assertEquals(expected.getId(), actual.getId(), "id");
        assertEquals(expected.getName(), actual.getName(), "name");
        assertEquals(expected.getDescription(), actual.getDescription(), "description");
        assertEquals(expected.getStatus(), actual.getStatus(), "status");
    }
}