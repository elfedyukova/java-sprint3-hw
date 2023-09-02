package org.example.managers;

import org.example.task.Task;

import java.util.List;

public interface HistoryManager {
    List<Task> getHistory();

    void add(Task tsk);

    void remove(int id);

}
