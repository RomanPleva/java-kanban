package ru.practicum.tasktracker.service;

import ru.practicum.tasktracker.model.Epic;
import ru.practicum.tasktracker.model.Subtask;
import ru.practicum.tasktracker.model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    List<Epic> getEpics();

    List<Task> getTasks();

    List<Subtask> getSubtasks();

    List<Task> getHistory();

    void removeTaskById(int id);

    void removeSubtaskById(int id);

    void removeEpicById(int id);

    int generateId();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);

    ArrayList<Subtask> getEpicSubtasksList(int epicId);

    Task createTask(Task task);

    Epic createEpic(Epic epic);

    Subtask createSubtask(Subtask subtask);

    Task updateTask(Task task);

    Epic updateEpic(Epic epic);

    Subtask updateSubtask(Subtask subtask);

    void updateEpicStatus(Epic epic);
}
