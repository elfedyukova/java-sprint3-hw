package org.example.managers.file;

import org.example.managers.HistoryManager;
import org.example.task.*;

import java.util.ArrayList;
import java.util.List;

public class CSVFormatHandler {

    private final String DELIMITER = ",";

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

        String[] fields = value.split(DELIMITER);

        int id = Integer.parseInt(fields[0]);
        TaskType type = TaskType.valueOf(fields[1]);
        String name = fields[2];
        TaskStatus taskStatus = TaskStatus.valueOf(fields[3]);
        String description = fields[4];


        switch (type) {
            case TASK:
                return new Task(name, description, taskStatus, id);
            case SUBTASK:
                int epicId = Integer.parseInt(fields[5]);
                return new Subtask(name, description, taskStatus, id, epicId);
            case EPIC:
                return new Epic(name, description, id);
        }

        return null;
    }

    String historyToString(HistoryManager manager) {
        List<String> result = new ArrayList<>();

        for (Task task : manager.getHistory()) {
            result.add(String.valueOf(task.getId()));
        }
        return String.join(DELIMITER, result);
    }

    List<Integer> historyFromString(String value) {
        List<Integer> list = new ArrayList<>();
        String[] ids = value.split(DELIMITER);
        for (String i : ids) {
            list.add(Integer.parseInt(i));
        }
        return list;
    }

    String getHeader() {
        return "id,type,name,status,description,epic";
    }
}
