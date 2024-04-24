package service;
import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Subtask> subtasks;
    private int serialN = 0;

    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    public void tasksRemoveById(int id) {
        tasks.remove(id);
    }

    public void subtasksRemoveById(int id, Epic epic) {
        subtasks.remove(id);
        epic.removeSubtaskById(id);
    }

    public void epicsRemoveById(int id) {
        epics.remove(id);
    }

    public TaskManager(){
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subtasks = new HashMap<>();
    }

    private int generateiD() {
        return ++serialN;
    }

    public Task getTaskByID(int id) {
        return tasks.get(id);
    }

    public Epic getEpicByID(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskByID(int id) {
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

    public ArrayList<Subtask> epicSubslist(Epic epic) {
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

    public Subtask createSubtask (Subtask subtask, Epic epic) {
        subtask.setId(generateiD());
        epic.addSubtask(subtask);
        subtask.setEpic(epic);
        subtasks.put(subtask.getId(), subtask);
        epic.updateStatus();
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

    public void removeSubtask(Subtask subtask, Epic epic) {
        subtasks.remove(subtask.getId());
        epic.removeSubtask(subtask);
        epic.updateStatus();
        updateEpic(epic);
        updateSubtask(subtask);
    }

    public void removeTask(Task task) {
        tasks.remove(task.getId());
    }

    public void removeEpic(Epic epic) {
        epics.remove(epic.getId());
        updateEpic(epic);
    }
}
