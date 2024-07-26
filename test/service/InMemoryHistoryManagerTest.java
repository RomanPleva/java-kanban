package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import ru.practicum.tasktracker.model.Task;
import ru.practicum.tasktracker.model.TaskStatus;
import ru.practicum.tasktracker.service.InMemoryHistoryManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Менеджер Истории")
class InMemoryHistoryManagerTest {
    private static InMemoryHistoryManager historyManager;
    private Task task;

    @BeforeEach
    void init() {
        historyManager = new InMemoryHistoryManager();
        task = new Task("name", "desc", TaskStatus.NEW);
    }

    @Test
    @DisplayName("Проверка на добавление задачи в историю")
    void shouldAddTaskToHistory() {
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не должна быть пустой");
        assertEquals(historyManager.getHistory(), history, "Задача должна быть добавлена");
    }
}