package ru.practicum.task_tracker.model;

public class Subtask extends Task {
    private Epic epic;

    public Subtask(String name, String description, TaskStatus status, Epic epic) {
        super(name, description, status);
        this.epic = epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    public Epic getEpic() {
        return epic;
    }

    public void updateStatus(Subtask subtask) {
        epic.addSubtask(subtask);
        epic.updateStatus();
    }

    public void removeSubtask(Subtask subtask) {
        epic.removeSubtask(subtask);
        epic.updateStatus();
    }

    public void removeById(int id) {
        if (epic.getId() == id) {
            for (Subtask subId : epic.getSubtasks()) {
                if (subId.getId() == id) {
                    epic.removeSubtask(subId);
                }
            }
        }
        epic.updateStatus();
    }
}
