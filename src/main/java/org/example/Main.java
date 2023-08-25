package org.example;

import org.example.managers.Managers;
import org.example.managers.TaskManager;
import org.example.task.Epic;
import org.example.task.Subtask;
import org.example.task.Task;
import org.example.task.TaskStatus;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        int id = 2;
        TaskManager taskManager = Managers.getDefault();

        for (int i = 0; i < 3; i++) {
            taskManager.createTask(new Task("task", "Создание задачи"));
        }

        for (int i = 0; i < 3; i++) {
            taskManager.createEpic(new Epic("epic", "Создание эпика"));
        }

        for (int i = 0; i < 3; i++) {
            int epicId = 4;
            taskManager.createSubtask(new Subtask("subtask", "Первый subtask", epicId));
        }

        taskManager.createSubtask(new Subtask("subtask", "Второй subtask", 6));

        List<Task> task = taskManager.getTasks();
        System.out.println("Количество текущих задач: " + task.size());
        for (int i = 0; i < task.size(); i++) {
            System.out.println("Тип: " + task.get(i).getName()
                    + ". Описание: " + task.get(i).getDescription()
                    + ". Статус: " + task.get(i).getStatus()
                    + ". ID: " + task.get(i).getId());
        }
        taskManager.updateTask(new Task("New task", "New Description", TaskStatus.IN_PROGRESS, id));
        System.out.println(taskManager.getTaskById(id));
        taskManager.deleteTaskById(id);
        System.out.println("Количество задач после удаления одной задачи по ID: " + taskManager.getTasks().size());
        taskManager.deleteTasks();
        System.out.println("Список задач после удаления всех задач: " + taskManager.getTasks().size());

        List<Subtask> subtasks = taskManager.getSubtasks();
        System.out.println("\nКоличество текущих сабтасков: " + subtasks.size());
        for (int i = 0; i < subtasks.size(); i++) {
            System.out.println("Тип: " + subtasks.get(i).getName()
                    + ". Описание: " + subtasks.get(i).getDescription()
                    + ". Статус: " + subtasks.get(i).getStatus()
                    + ". ID: " + subtasks.get(i).getId()
                    + ". Epic ID: " + subtasks.get(i).getEpicId());
        }
        int subtaskId = 7;
        taskManager.updateSubtask(new Subtask("new name", "new description", TaskStatus.IN_PROGRESS, 4), subtaskId);
        System.out.println(taskManager.getSubtaskById(subtaskId));
        taskManager.deleteSubtaskById(subtaskId);
        System.out.println("Список сабтасков после удаления одного по ID: " + +taskManager.getSubtasks().size());
        taskManager.deleteSubtasks();
        System.out.println("Список сабтасков после удаления всех: " + taskManager.getSubtasks().size());

        List<Epic> epic = taskManager.getEpics();
        System.out.println("\nКоличество текущих эпиков: " + epic.size());
        for (int i = 0; i < epic.size(); i++) {
            System.out.println("Тип: " + epic.get(i).getName()
                    + ". Описание: " + epic.get(i).getDescription()
                    + ". Статус: " + epic.get(i).getStatus()
                    + ". ID: " + epic.get(i).getId());
        }
        int epicId = 6;
        System.out.println("Получение эпика по ID = 6 " + taskManager.getEpicById(epicId));
        taskManager.updateEpic(new Epic("new epic", "new description", epicId));
        System.out.println("Обновление эпика по ID = 6" + taskManager.getEpicById(epicId));
        int epicIdForSubtask = 4;
        System.out.println("Количество подзадач у эпика = " + epicIdForSubtask
                + ": " + taskManager.getSubtasksFromEpicById(epicIdForSubtask).size());
        taskManager.deleteEpicById(epicId);
        System.out.println("Количество эпиков после удаления одного по ID = " + taskManager.getEpics().size());
        taskManager.deleteEpics();
        System.out.println("Список эпиков после удаления всех: " + taskManager.getEpics().size());
        System.out.println("Список сабтасков после удаления эпиков: " + taskManager.getSubtasks().size());

        List<Task> history = taskManager.getHistory();
        System.out.println("Было просмотрено задач: " + history.size());

    }
}