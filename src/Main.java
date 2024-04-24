import model.Epic;
import model.Status;
import model.Subtask;
import service.TaskManager;
import model.Task;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task = taskManager.createTask(new Task("Новая задача1", "Описание1", Status.NEW));
        Task task2 = taskManager.createTask(new Task("Новая задача2", "Описание2", Status.NEW));

        Epic epic = taskManager.createEpic(new Epic("Новый эпик1", "Описание эпика1"));
        Subtask subtask = taskManager.createSubtask(new Subtask("Имя подзадачи1", "Описание1",
                Status.NEW), epic);
        Subtask subtask2 = taskManager.createSubtask(new Subtask("Имя подзадачи2", "Описание2",
                Status.IN_PROGRESS), epic);

        Epic epic2 = taskManager.createEpic(new Epic("Новый эпик2", "Описание эпика2"));
        Subtask subtask3 = taskManager.createSubtask(new Subtask("Имя подзадачи3", "Описание3",
                Status.DONE), epic2);

        System.out.println(taskManager.epicsList());
        System.out.println(taskManager.tasksList());
        System.out.println(taskManager.subtasksList());

        subtask.setStatus(Status.DONE);
        subtask2.setStatus(Status.DONE);
        epic.updateStatus();

        System.out.println(epic);

        taskManager.removeTask(task);
        taskManager.removeEpic(epic);

    }
}
