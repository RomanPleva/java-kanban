package ru.practicum.tasktracker.service;
import ru.practicum.tasktracker.model.Epic;
import ru.practicum.tasktracker.model.Subtask;
import ru.practicum.tasktracker.model.Task;
import ru.practicum.tasktracker.model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    final HashMap<Integer, Epic> epics;
    final HashMap<Integer, Task> tasks;
    final HashMap<Integer, Subtask> subtasks;

    final HistoryManager historyManager;
    int serialN;

    public InMemoryTaskManager(HistoryManager historyManager){
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.historyManager = historyManager;
    }

    @Override
    public List<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    @Override
    public void removeSubtaskById(int id) {
        epics.get(subtasks.get(id).getEpic().getSubtasks().remove(subtasks.get(id)));
        updateEpicStatus(subtasks.get(id).getEpic());
        subtasks.remove(id);
    }

    @Override
    public void removeEpicById(int id) {
        epics.remove(id);
    }

    @Override
    public int generateId() {
        return ++serialN;
    }

    @Override
    public Task getTaskById(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        historyManager.add(subtasks.get(id));
        return subtasks.get(id);
    }

    @Override
    public ArrayList<Subtask> getEpicSubtasksList(int epicId) {
        return epics.get(epicId).getSubtasks();
    }

    @Override
    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epic.setStatus(TaskStatus.NEW);
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public Subtask createSubtask(Subtask subtask) {
        subtask.setId(generateId());
        subtasks.put(subtask.getId(), subtask);
        subtask.getEpic().getSubtasks().add(subtask);
        updateEpicStatus(subtask.getEpic());
        return subtask;
    }

    @Override
    public Task updateTask(Task task) {
        Task saved = tasks.get(task.getId());
        if (saved == null) {
            return null;
        }
        tasks.put(saved.getId(), saved);
        return task;
    }

    @Override
    public Epic updateEpic(Epic epic) {
        Epic saved = epics.get(epic.getId());
        if (saved == null) {
            return null;
        }
        saved.setName(epic.getName());
        saved.setDescription(epic.getDescription());
        saved.setStatus(epic.getStatus());
        epics.put(saved.getId(), saved);
        updateEpicStatus(epic);
        return epic;
    }

    @Override
    public Subtask updateSubtask(Subtask subtask) {
        Subtask saved = subtasks.get(subtask.getId());
        if (saved == null) {
            return null;
        }
        saved.setName(subtask.getName());
        saved.setDescription(subtask.getDescription());
        saved.setStatus(subtask.getStatus());
        subtasks.put(saved.getId(), saved);
        return subtask;
    }

    @Override
    public void updateEpicStatus(Epic epic) {
        int statusProgress = 0;
        int statusDone = 0;
        int statusNew = 0;
        if (epic.getSubtasks() != null) {
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
            } else if (statusProgress > 0 && statusNew > 0 && statusDone > 0) {
                epic.setStatus(TaskStatus.IN_PROGRESS);
            } else if (statusProgress > 0 && statusNew >= 0 && statusDone >= 0) {
                epic.setStatus(TaskStatus.IN_PROGRESS);
            } else {
                epic.setStatus(TaskStatus.NEW);
            }
        }
    }
}