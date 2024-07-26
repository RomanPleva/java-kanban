package service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.tasktracker.model.Epic;
import ru.practicum.tasktracker.model.Task;
import ru.practicum.tasktracker.model.Subtask;
import ru.practicum.tasktracker.model.TaskStatus;
import ru.practicum.tasktracker.service.InMemoryHistoryManager;
import ru.practicum.tasktracker.service.InMemoryTaskManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Таск менеджер")
class InMemoryTaskManagerTest {
    InMemoryTaskManager taskManager;

    @BeforeEach
    void init() {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        taskManager = new InMemoryTaskManager(historyManager);
    }

    @Test
    @DisplayName("Добавление задачи")
    void shouldAddTask() {
        Task task = new Task("taskName", "taskDesc", TaskStatus.NEW);
        Task savedTask = taskManager.createTask(task);

        assertNotNull(savedTask, "Задача должна быть добавлена");
        assertEquals(task, savedTask, "Задачи должны совпадать");

        final List<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи должны возвращаться");
        assertEquals(1, tasks.size(), "Количество задач должно совпадать");
        assertEquals(task, tasks.get(0), "Задачи должны совпадать");
    }

    @Test
    @DisplayName("Добавление эпика")
    void shouldAddEpic() {
        Epic epic = new Epic("epicName", "epicDesc");
        Epic savedEpic = taskManager.createEpic(epic);

        assertNotNull(savedEpic, "Эпик должен быть добавлен");
        assertEquals(epic, savedEpic, "Эпики должны совпадать");

        final List<Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Эпики должны возвращаться");
        assertEquals(1, epics.size(), "Количество эпиков должно совпадать");
        assertEquals(epic, epics.get(0), "Эпики должны совпадать");
    }

    @Test
    @DisplayName("Добавление сабтаска")
    void shouldAddSubtask() {
        Epic epic = new Epic("epicName", "epicDesc");
        taskManager.createEpic(epic);
        Subtask subtask = new Subtask("subtaskName", "subtaskDesc", TaskStatus.NEW, epic);
        Subtask savedSubtask = taskManager.createSubtask(subtask);

        assertNotNull(savedSubtask, "Субтаск должнен быть добавлен");
        assertEquals(subtask, savedSubtask, "Субтаски должны совпадать");

        final List<Subtask> subtasks = taskManager.getSubtasks();

        assertNotNull(subtasks, "Субтаски должны возвращаться");
        assertEquals(1, subtasks.size(), "Количество субтасков должно совпадать");
        assertEquals(subtask, subtasks.get(0), "Субтаски должны совпадать");
    }

    @Test
    @DisplayName("Поиск задачи по ID")
    void shouldGetTaskById() {
        Task task = new Task("taskName", "taskDesc", TaskStatus.NEW);
        taskManager.createTask(task);
        Task getTask = taskManager.getTaskById(task.getId());

        assertEquals(task, getTask, "Задачи должнЫ совпадать");
    }

    @Test
    @DisplayName("Поиск эпика по ID")
    void shouldGetEpicById() {
        Epic epic = new Epic("epicName", "epicDesc");
        taskManager.createEpic(epic);
        Epic getEpic = taskManager.getEpicById(epic.getId());

        assertEquals(epic, getEpic, "Эпики должны совпадать");
    }

    @Test
    @DisplayName("Поиск субтаска по ID")
    void shouldGetSubtaskById() {
        Epic epic = new Epic("epicName", "epicDesc");
        taskManager.createEpic(epic);
        Subtask subtask = new Subtask("subtaskName", "subtaskDesc", TaskStatus.NEW, epic);
        taskManager.createSubtask(subtask);

        Subtask getSubtask = taskManager.getSubtaskById(subtask.getId());

        assertEquals(subtask, getSubtask, "Субтаски должны совпадать");
    }

    @Test
    @DisplayName("Проверка обновления задачи")
    void shouldUpdateTask() {
        Task task = new Task("taskName", "taskDesc", TaskStatus.NEW);
        taskManager.createTask(task);

        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setName("newTaskName");
        task.setDescription("newTaskDesc");

        taskManager.updateTask(task);

        assertEquals(TaskStatus.IN_PROGRESS, taskManager.getTaskById(task.getId()).getStatus(),
                "Статусы задач должны быть равны");
        assertEquals("newTaskName", taskManager.getTaskById(task.getId()).getName(),
                "Имена задач должны быть равны");
        assertEquals("newTaskDesc", taskManager.getTaskById(task.getId()).getDescription(),
                "Описания задач должны быть равны");
    }

    @Test
    @DisplayName("Проверка обновления эпика")
    void shouldUpdateEpic() {
        Epic epic = new Epic("epicName", "epicDesc");
        taskManager.createEpic(epic);

        epic.setName("newEpicName");
        epic.setDescription("newEpicDesc");

        taskManager.updateEpic(epic);

        assertEquals("newEpicName", taskManager.getEpicById(epic.getId()).getName(),
                "Имена эпиков должны быть равны");
        assertEquals("newEpicDesc", taskManager.getEpicById(epic.getId()).getDescription(),
                "Описания эпиков должны быть равны");
    }

    @Test
    @DisplayName("Проверка обновления субтаска")
    void shouldUpdateSubtask() {
        Epic epic = new Epic("epicName", "epicDesc");
        taskManager.createEpic(epic);

        Subtask subtask = new Subtask("subtaskName", "subtaskDesc", TaskStatus.NEW, epic);
        taskManager.createSubtask(subtask);

        subtask.setStatus(TaskStatus.IN_PROGRESS);
        subtask.setName("newSubtaskName");
        subtask.setDescription("newSubtaskDesc");

        taskManager.updateSubtask(subtask);

        assertEquals(TaskStatus.IN_PROGRESS, taskManager.getSubtaskById(subtask.getId()).getStatus(),
                "Статусы субтасков должны быть равны");
        assertEquals("newSubtaskName", taskManager.getSubtaskById(subtask.getId()).getName(),
                "Имена задач должны быть не равны");
        assertEquals("newSubtaskDesc", taskManager.getSubtaskById(subtask.getId()).getDescription(),
                "Описания задач должны быть не равны");
    }

    @Test
    @DisplayName("Проверка обновления статуса эпика")
    void shouldUpdateEpicStatus() {
        Epic epic = new Epic("epicName", "epicDesc");
        taskManager.createEpic(epic);

        assertEquals(TaskStatus.NEW, taskManager.getEpicById(epic.getId()).getStatus(),
                "Статусы эпиков должны совпадать");


        Subtask subtask = new Subtask("subtaskName", "subtaskDesc", TaskStatus.IN_PROGRESS, epic);
        taskManager.createSubtask(subtask);

        assertEquals(TaskStatus.IN_PROGRESS, taskManager.getEpicById(epic.getId()).getStatus(),
                "Статусы эпиков должны совпадать");
    }

    @Test
    @DisplayName("Проверка удаления задач")
    void shouldRemoveTaskById() {
        Task task = new Task("taskName", "taskDesc", TaskStatus.NEW);
        Task savedTask = taskManager.createTask(task);

        assertNotNull(savedTask);
        assertEquals(1, taskManager.getTasks().size(), "Списки задач должны быть равны");

        taskManager.removeTaskById(1);
        assertEquals(0, taskManager.getTasks().size(),
                "Списки задач после удаления должны быть равны");
    }

    @Test
    @DisplayName("Проверка удаления эпиков")
    void shouldRemoveEpicById() {
        Epic epic = new Epic("taskName", "taskDesc");
        Epic savedEpic = taskManager.createEpic(epic);

        assertNotNull(savedEpic);
        assertEquals(1, taskManager.getEpics().size(), "Списки эпиков должны быть равны");

        taskManager.removeEpicById(1);
        assertEquals(0, taskManager.getEpics().size(),
                "Списки эпиков после удаления должны быть равны");
    }

    @Test
    @DisplayName("Проверка удаления субтасков")
    void shouldRemoveSubtaskById() {
        Epic epic = new Epic("taskName", "taskDesc");
        taskManager.createEpic(epic);

        Subtask subtask = new Subtask("subtaskName", "subtaskDesc", TaskStatus.NEW, epic);
        Subtask savedSubtask = taskManager.createSubtask(subtask);

        assertNotNull(savedSubtask);
        assertEquals(1, taskManager.getSubtasks().size(), "Списки субтасков должны быть равны");

        taskManager.removeSubtaskById(2);
        assertEquals(0, taskManager.getSubtasks().size(),
                "Списки субтасков после удаления должны быть равны");
    }
}