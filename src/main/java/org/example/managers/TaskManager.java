package org.example.managers;

import org.example.task.Epic;
import org.example.task.Subtask;
import org.example.task.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    Task createTask(Task task);

    ArrayList<Task> getTasks();

    Task getTaskById(int id);

    void updateTask(Task task);
    void deleteTasks();
    void deleteTaskById(int id);

    Epic createEpic(Epic epic);

    ArrayList<Epic> getEpics();

    Epic getEpicById(int id);

    void updateEpic(Epic epic);

    void deleteEpicById(int epicId);

    void deleteEpics();

    ArrayList<Subtask> getSubtasksFromEpicById(int epicId);

    void updateEpicStatus(Epic epic);

    Subtask createSubtask(Subtask subtask);

    ArrayList<Subtask> getSubtasks();

    Subtask getSubtaskById(int id);

    void updateSubtask(Subtask subtask, int subtaskId);

    void deleteSubtasks();

    void deleteSubtaskById(int subtaskId);

    List<Task> getHistory();

}

