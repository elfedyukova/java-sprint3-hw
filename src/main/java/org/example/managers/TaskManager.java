package org.example.managers;

import org.example.task.Epic;
import org.example.task.Subtask;
import org.example.task.Task;

import java.util.List;

public interface TaskManager {
    Task createTask(Task task);

    List<Task> getTasks();

    Task getTaskById(int id);

    void updateTask(Task task);

    void deleteTasks();

    void deleteTaskById(int id);

    Epic createEpic(Epic epic);

    List<Epic> getEpics();

    Epic getEpicById(int id);

    void updateEpic(Epic epic);

    void deleteEpicById(int epicId);

    void deleteEpics();

    List<Subtask> getSubtasksFromEpicById(int epicId);

    Subtask createSubtask(Subtask subtask);

    List<Subtask> getSubtasks();

    Subtask getSubtaskById(int id);

    void updateSubtask(Subtask subtask);

    void deleteSubtasks();

    void deleteSubtaskById(int subtaskId);

    List<Task> getHistory();

}

