package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import ru.practicum.tasktracker.model.Task;
import ru.practicum.tasktracker.model.TaskStatus;
import ru.practicum.tasktracker.service.InMemoryHistoryManager;

import java.util.ArrayList;
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
        task = new Task("name", "desc", TaskStatus.NEW, 0);
    }

    @Test
    @DisplayName("Проверка на добавление задачи в историю")
    void shouldAddTaskToHistory() {
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не должна быть пустой");
        assertEquals(historyManager.getHistory(), history, "Задача должна быть добавлена");
    }

    @Test
    @DisplayName("Проверка возврата списка истории")
    void shouldBeAnEmptyHistory() {
        final List<Task> emptyHistory = historyManager.getHistory();
        assertEquals(0, emptyHistory.size(), "История пустая");
    }

    @Test
    @DisplayName("Проверка на отсутствие дублирования задачи")
    void shouldNotDoubleTaskInHistory() {
        historyManager.add(task);
        final List<Task> listFromNodeMap1 = historyManager.getHistory();
        historyManager.add(task);
        final List<Task> listFromNodeMap2 = historyManager.getHistory();
        assertEquals(listFromNodeMap2.size(), listFromNodeMap1.size(),
                "Мапа истории дублирует задачи");
    }

    @Test
    @DisplayName("Проверка на удаление задачи из списка истории")
    void shouldRemoveNodeFromLinkedHashMap() {
        historyManager.add(task);
        final List<Task> nodeMap1 = historyManager.getHistory();
        assertNotNull(nodeMap1, "Мапа не пустая.");

        historyManager.remove(task.getId());
        final List<Task> nodeMap2 = historyManager.getHistory();
        final List<Task> nullList = new ArrayList<>();
        assertEquals(nodeMap2.size(), nullList.size(), "Мапа не пустая.");
    }

    @Test
    @DisplayName("Проверка на удаление задачи из середины")
    void shouldRemoveNodeFromMiddleOfLinkedHashMap() {
        Task task1 = new Task("name1", "desc2", TaskStatus.NEW, 1);
        Task task2 = new Task("name2", "desc2", TaskStatus.NEW, 2);
        historyManager.add(task);
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.remove(task1.getId());
        final List<Task> listFromNodeMap = historyManager.getHistory();
        assertEquals(listFromNodeMap.size(), List.of(task, task2).size(),
                "Мапа не соответствует коректому размеру");
    }
}