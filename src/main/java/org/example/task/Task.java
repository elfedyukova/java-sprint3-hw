package org.example.task;
public class Task {

    protected String name;
    protected String description;
    protected String status;
    protected int id;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = "NEW";

    }

    public Task(String name, String description, String status, int id) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public int getId() {
        return this.id;
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