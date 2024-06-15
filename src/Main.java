import ru.practicum.task_tracker.model.Epic;
import ru.practicum.task_tracker.model.TaskStatus;
import ru.practicum.task_tracker.model.Subtask;
import ru.practicum.task_tracker.service.TaskManager;
import ru.practicum.task_tracker.model.Task;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task = taskManager.createTask(new Task("Новая задача1", "Описание1", TaskStatus.NEW));
        Task task2 = taskManager.createTask(new Task("Новая задача2", "Описание2", TaskStatus.NEW));

        Epic epic = taskManager.createEpic(new Epic("Новый эпик1", "Описание эпика1"));
        Subtask subtask = new Subtask("Имя подзадачи1", "Описание1", TaskStatus.NEW, epic);
        Subtask subtask2 = new Subtask("Имя подзадачи2", "Описание2", TaskStatus.IN_PROGRESS, epic);
        taskManager.createSubtask(subtask);
        taskManager.createSubtask(subtask2);

        Epic epic2 = taskManager.createEpic(new Epic("Новый эпик2", "Описание эпика2"));
        Subtask subtask3 = new Subtask("Имя подзадачи3", "Описание3",
                TaskStatus.DONE, epic2);
        taskManager.createSubtask(subtask3);

        System.out.println(taskManager.epicsList());
        System.out.println(taskManager.tasksList());
        System.out.println(taskManager.subtasksList());

        subtask.setStatus(TaskStatus.DONE);
        subtask2.setStatus(TaskStatus.DONE);
        epic.updateStatus();

        System.out.println(epic);

        taskManager.removeTask(task);
        taskManager.removeEpic(epic);

        System.out.println(epic.getSubtasks());

        taskManager.subtasksRemoveById(7);

        System.out.println(taskManager.getSubtasks());

    }
}