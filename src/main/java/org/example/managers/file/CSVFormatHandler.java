package org.example.managers.file;

import org.example.managers.HistoryManager;
import org.example.task.Subtask;
import org.example.task.Task;
import org.example.task.TaskType;

import java.util.ArrayList;
import java.util.List;

//для сохранения и восстановления менеджера истории из CSV.

public class CSVFormatHandler {

    private static final String DELIMITER = ",";

    String toString(Task task) {
        //1,TASK,Task1,NEW,Description task1, на выходе должны получить
        String result = task.getId() + DELIMITER +
                task.getType() + DELIMITER +
                task.getName() + DELIMITER +
                task.getStatus() + DELIMITER +
                task.getDescription() + DELIMITER;

        if (task.getType() == TaskType.SUBTASK) {
            result = result + ((Subtask) task).getEpicId();//конвертируем таску в сабтаск
        }
        return result;
    }

    Task fromString(String value) {
//Напишите метод создания задачи из строки Task fromString(String value).
        String id = "";
        String taskType = "";
        //по второму параметру определяем тип задач
        TaskType type = TaskType.valueOf(taskType);
        //создаем новый объект через конструктор
        Task task = new Task("task", "Создание задачи");

        return task;
    }

    String historyToString(HistoryManager manager) {
        //получаем данные связного  списка истории + проходимся и в каждой задаче получайем id  + джойним через стрингджойн
        // каждая строка это идентификатор
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
