package org.example.task;

import java.util.Objects;

public class Subtask extends Task {

    private int epicId;

    public Subtask(String name, String description, int epicId) {
        super(name, description);
        this.epicId = epicId;
        this.type = TaskType.SUBTASK;
    }

    public Subtask(String name, String description, TaskStatus status, int id, int epicId) {
        super(name, description);
        this.id = id;
        this.status = status;
        this.epicId = epicId;
        this.type = TaskType.SUBTASK;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // проверяем адреса объектов
        if (obj == null) return false; // проверяем ссылку на null
        if (this.getClass() != obj.getClass()) return false; // сравниваем классы объектов
        Task otherTask = (Task) obj; // открываем доступ к полям другого объекта
        return Objects.equals(name, otherTask.name) && // проверяем все поля
                Objects.equals(description, otherTask.description) &&
                Objects.equals(status, otherTask.status);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

}