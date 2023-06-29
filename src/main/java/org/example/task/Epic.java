package org.example.task;

import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {

    private HashMap<Integer, Subtask> subtasks = new HashMap<>(); //Может все таки List используешь?
    //ArrayList<Subtask> subtasks;
    //protected int epicId;

    public Epic(String name, String description) {
        super(name, description, "NEW");

    }

    public Epic(String name, String description, String status, int id) {
        super(name, description, "NEW");
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
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

   // public ArrayList<Subtask> getSubTasks() {
    //    return subtasks;
    //}
    public HashMap<Integer, Subtask> getSubTasks() {
        return subtasks;
    }
}
