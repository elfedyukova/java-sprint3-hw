package org.example.task;

public class Subtask extends Task {

    private int epicId;

    public Subtask(String name, String description) {
        super(name, description, "NEW");
        this.epicId = epicId;
    }

    public Subtask(String name, String description, String status) {
        super(name, description, status);
        this.epicId = epicId;
    }
    public Subtask(String name, String description, int epicId) {
        super(name, description, "NEW", epicId);
        this.setEpicId(epicId);
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;

    }

    public int getEpicId() {
        return epicId;
    }

    public int getId() {
        return id;
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
}
