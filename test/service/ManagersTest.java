package service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.practicum.tasktracker.service.HistoryManager;
import ru.practicum.tasktracker.service.Managers;
import ru.practicum.tasktracker.service.TaskManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Менеджер")
class ManagersTest {

    @Test
    @DisplayName("Инициализация таск менеджера")
    void shouldInitDef() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager, "Таск менеджер должен быть проинициализирован");
    }

    @Test
    @DisplayName("Инициализация менеджера истории")
    void shouldInitDefHistory() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager, "Таск менеджер должен быть проинициализирован");
    }
}