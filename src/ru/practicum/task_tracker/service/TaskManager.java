package ru.practicum.task_tracker.service;
import ru.practicum.task_tracker.model.Epic;
import ru.practicum.task_tracker.model.Subtask;
import ru.practicum.task_tracker.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Subtask> subtasks;
    private int serialN = 0;

    public TaskManager(){
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subtasks = new HashMap<>();
    }

    public List<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void tasksRemoveById(int id) {
        tasks.remove(id);
    }

    public void subtasksRemoveById(int id) {
        subtasks.remove(id);
    }

    public void epicsRemoveById(int id) {
        epics.remove(id);
    }

    private int generateiD() {
        return ++serialN;
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public HashMap tasksList() {
        return tasks;
    }

    public HashMap epicsList() {
        return epics;
    }

    public HashMap subtasksList() {
        return subtasks;
    }

    public ArrayList<Subtask> epicSubtaskList(Epic epic) {
        return epic.getSubtasks();
    }

    public Task createTask(Task task) {
        task.setId(generateiD());
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic createEpic(Epic epic) {
        epic.setId(generateiD());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Subtask createSubtask (Subtask subtask) {
        subtask.setId(generateiD());
        subtasks.put(subtask.getId(), subtask);
        subtask.updateStatus(subtask);
        return subtask;
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        Epic saved = epics.get(epic.getId());
        if (saved == null) {
            return;
        }
        saved.setName(epic.getName());
        saved.setDescription(epic.getDescription());
        saved.setStatus(epic.getStatus());
        epics.put(saved.getId(), saved);
        epics.remove(epic);
    }

    public void updateSubtask(Subtask subtask) {
        Subtask saved = subtasks.get(subtask.getId());
        if (saved == null) {
            return;
        }
        saved.setName(subtask.getName());
        saved.setDescription(subtask.getDescription());
        saved.setStatus(subtask.getStatus());
        subtasks.put(saved.getId(), saved);
        subtasks.remove(subtask);
    }

    public void removeSubtask(Subtask subtask) {
        subtasks.remove(subtask);
        subtask.removeSubtask(subtask);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void removeEpic(Epic epic) {
        epics.remove(epic);
        updateEpic(epic);
    }
}
