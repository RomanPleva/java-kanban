package ru.practicum.tasktracker.model;

import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Subtask> subtasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description, null);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }
}