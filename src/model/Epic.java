package model;

import java.util.ArrayList;

public class Epic extends Task{
    final private ArrayList<Integer> subtaskId = new ArrayList<>();
    final private ArrayList<Subtask> subtasks = new ArrayList<>();

    public Epic(String name, String description, Status status) {
        super(name, description, status);
    }

    public Epic(String name, String description) {
        super(name, description, null);
    }

    public ArrayList<Integer> getSubtaskId() {
        return subtaskId;
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }
    public void updateStatus() {
        setStatus(Status.NEW);
        int progress = 0;
        int done = 0;
        for (Subtask subStatus : subtasks ) {
            if (subStatus.getStatus() == Status.DONE) {
                done++;
            } else if (subStatus.getStatus() == Status.IN_PROGRESS) {
                progress++;
            }
        }
        if (progress == 0 && done > 0) {
            setStatus(Status.DONE);
        } else if (progress > 0) {
            setStatus(Status.IN_PROGRESS);
        }
    }

    public void addSubtask(Subtask subtask) {
        subtaskId.add(subtask.getId());
        subtasks.add(subtask);
        setStatus(Status.NEW);
    }

    public void removeSubtask(Subtask subtask) {
        if (subtasks.contains(subtask)) {
            subtasks.remove(subtask);
            int index = subtaskId.indexOf(subtask.getId());
            subtaskId.remove(index);
        }
    }
    public void removeSubtaskById(int id) {
        if (subtaskId.contains(id)) {
            int index = subtaskId.indexOf(id);
            subtaskId.remove(index);
            subtasks.remove(index);
        }
    }
}
