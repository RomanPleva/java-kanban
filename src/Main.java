import ru.practicum.tasktracker.model.Epic;
import ru.practicum.tasktracker.model.TaskStatus;
import ru.practicum.tasktracker.model.Subtask;
import ru.practicum.tasktracker.model.Task;
import ru.practicum.tasktracker.service.*;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();

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

        System.out.println("======");
        System.out.println("Печать всех задач");
        System.out.println("======");

        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());

        System.out.println("======");
        System.out.println("Обновление статуса подзадач эпика1");
        System.out.println("======");

        subtask.setStatus(TaskStatus.DONE);
        subtask2.setStatus(TaskStatus.IN_PROGRESS);

        System.out.println("======");
        System.out.println("Проверка статуса эпика1");
        System.out.println("======");

        taskManager.updateEpic(epic);
        System.out.println(taskManager.getEpicById(3));

        System.out.println("======");
        System.out.println("печать эпиков");

        System.out.println(taskManager.getEpics());

        taskManager.removeSubtaskById(4);

        System.out.println(taskManager.getEpicSubtasksList(3));

        System.out.println("=====");
        System.out.println(task.getName());
        System.out.println("=====");
        task.setName("Новое имя");
        System.out.println(task.getName());
        System.out.println(taskManager.getTasks());
    }
}