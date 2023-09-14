package org.example.managers.file;

import org.example.managers.HistoryManager;
import org.example.task.Subtask;
import org.example.task.Task;
import org.example.task.TaskType;

import java.util.ArrayList;
import java.util.List;

public class CSVFormatHandler {

    private static final String DELIMITER = ",";

    String toString(Task task) {
        String result = task.getId() + DELIMITER +
                task.getType() + DELIMITER +
                task.getName() + DELIMITER +
                task.getStatus() + DELIMITER +
                task.getDescription() + DELIMITER;

        if (task.getType() == TaskType.SUBTASK) {
            result = result + ((Subtask) task).getEpicId();
        }
        return result;
    }

    Task fromString(String value) {
        String id = "";
        String taskType = "";
        TaskType type = TaskType.valueOf(taskType);
        Task task = new Task("task", "Создание задачи");
        return task;
    }

    String historyToString(HistoryManager manager) {
        List<String> result = new ArrayList<>();

        for (Task task : manager.getHistory()) {
            result.add(String.valueOf(task.getId()));
        }
        return String.join(DELIMITER, result);
    }

    List<Task> historyFromString(String value) {
        List<Task> task = new ArrayList<>();
        String[] ids = value.split(",");
        for (String i : ids) {
            //  task.add();
        }
        return task;
    }

    String getHeader() {
        return "id, type, status, description, epic";
    }
}
