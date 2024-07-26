package ru.practicum.tasktracker.service;

import java.util.List;
import ru.practicum.tasktracker.model.Task;

public interface HistoryManager {
    void add(Task task);

    List<Task> getHistory();
}
