package ru.practicum.tasktracker.service;
import ru.practicum.tasktracker.model.Epic;
import ru.practicum.tasktracker.model.Subtask;
import ru.practicum.tasktracker.model.Task;
import ru.practicum.tasktracker.model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private final HashMap<Integer, Epic> epics;
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Subtask> subtasks;
    private int serialN;

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

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public void removeSubtaskById(int id) {
        epics.get(subtasks.get(id).getEpic().getSubtasks().remove(subtasks.get(id)));
        updateEpicStatus(subtasks.get(id).getEpic());
        subtasks.remove(id);
    }

    public void removeEpicById(int id) {
        epics.remove(id);
    }

    private int generateId() {
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

    public ArrayList<Subtask> getEpicSubtasksList(int epicId) {
        return epics.get(epicId).getSubtasks();
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Subtask createSubtask (Subtask subtask) {
        subtask.setId(generateId());
        subtasks.put(subtask.getId(), subtask);
        subtask.getEpic().getSubtasks().add(subtask);
        updateEpicStatus(subtask.getEpic());
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
        updateEpicStatus(epic);
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
    }

    private void updateEpicStatus(Epic epic) {
        int statusProgress = 0;
        int statusDone = 0;
        int statusNew = 0;
        for (Subtask subStatus : epic.getSubtasks() ) {
            if (subStatus.getStatus() == TaskStatus.DONE) {
                statusDone++;
            } else if (subStatus.getStatus() == TaskStatus.IN_PROGRESS) {
                statusProgress++;
            } else if (subStatus.getStatus() == TaskStatus.NEW) {
                statusNew++;
            }
        }
        if (statusProgress == 0 && statusDone > 0 && statusNew == 0) {
            epic.setStatus(TaskStatus.DONE);
        } else if (statusProgress >= 0 && statusNew > 0 && statusDone >= 0) {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        } else {
            epic.setStatus(TaskStatus.NEW);
        }
    }
}