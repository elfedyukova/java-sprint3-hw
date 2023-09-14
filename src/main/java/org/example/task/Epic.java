package org.example.task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private List<Subtask> subtasks;//

    public Epic(String name, String description) {
        super(name, description);
        this.type = TaskType.EPIC;
        subtasks = new ArrayList<>();
    }

    public Epic(String name, String description, int id) {
        super(name, description);
        this.id = id;
        this.type = TaskType.EPIC;
        subtasks = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public void removeSubtasks(int id) {
        subtasks.removeIf(t -> t.getId() == id);
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {

        return "{" + "name = '" + name + '\'' +
                ", details='" + description + '\'' +
                ", id='" + id + '\'' +
                ", status='" + status + '\'' +
                "}";
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

}