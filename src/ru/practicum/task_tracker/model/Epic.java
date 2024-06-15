package ru.practicum.task_tracker.model;

import java.util.ArrayList;

public class Epic extends Task{
    private final ArrayList<Subtask> subtasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description, null);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void updateStatus() {
        setStatus(TaskStatus.NEW);
        int progress = 0;
        int done = 0;
        for (Subtask subStatus : subtasks ) {
            if (subStatus.getStatus() == TaskStatus.DONE) {
                done++;
            } else if (subStatus.getStatus() == TaskStatus.IN_PROGRESS) {
                progress++;
            }
        }
        if (progress == 0 && done > 0) {
            setStatus(TaskStatus.DONE);
        } else if (progress > 0) {
            setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
        setStatus(TaskStatus.NEW);
    }

    public void removeSubtask(Subtask subtask) {
        if (subtasks.contains(subtask)) {
            subtasks.remove(subtask);
        }
    }
}