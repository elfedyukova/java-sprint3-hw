package org.example;

import org.example.managers.TaskManager;
import org.example.task.Epic;
import org.example.task.Subtask;
import org.example.task.Task;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int id = 2;
        TaskManager taskManager = new TaskManager();

        for (int i = 0; i < 3; i++) {
            taskManager.createTask(new Task("task", "Создание задачи"));
        }

        for (int i = 0; i < 3; i++) {
            taskManager.createEpic(new Epic("epic", "Создание эпика"));
        }

        for (int i = 0; i < 2; i++) {
            int epicId = 4;
            taskManager.createSubtask(new Subtask("subtask", "Первый subtask"), epicId);
        }

        taskManager.createSubtask(new Subtask("subtask", "Второй subtask"), 6);

        if (taskManager.tasks.isEmpty()) {
            System.out.println("Список задач пуст. Добавьте хотя бы одну задачу!");
        } else {
            ArrayList<Task> task = taskManager.getTasks();
            System.out.println("Количество текущих задач: " + task.size());
            for (int i = 0; i < task.size(); i++) {
                System.out.println("Тип: " + task.get(i).getName()
                        + ". Описание: " + task.get(i).getDescription()
                        + ". Статус: " + task.get(i).getStatus()
                        + ". ID: " + task.get(i).getId());
            }
            System.out.println("Получение задачи по ID = " + taskManager.getTaskById(id).getId()
                    + ". Тип: " + taskManager.getTaskById(id).getName()
                    + ". Описание: " + taskManager.getTaskById(id).getDescription()
                    + ". Статус: " + taskManager.getTaskById(id).getStatus()
                    + ". ID = " + taskManager.getTaskById(id).getId());
            taskManager.updateTask(new Task("New task", "New Description", "IN_PROGRESS", id));
            System.out.println("Обновление задачи по ID = " + taskManager.getTaskById(id).getId()
                    + ". Тип: " + taskManager.getTaskById(id).getName()
                    + ". Описание: " + taskManager.getTaskById(id).getDescription()
                    + ". Статус: " + taskManager.getTaskById(id).getStatus()
                    + ". ID = " + taskManager.getTaskById(id).getId());
            taskManager.deleteTaskById(id);
            System.out.println("Количество задач после удаления одной задачи по ID: " + taskManager.getTasks().size());
            taskManager.deleteTasks();
            if (taskManager.tasks.isEmpty()) {
                System.out.println("Список задач после удаления всех задач: " + taskManager.getTasks().size());
            }
        }

        if (taskManager.subtasks.isEmpty()) {
            System.out.println("Список сабтасков пуст. Добавьте хотя бы один сабтаск!");
        } else {
            ArrayList<Subtask> subtasks = taskManager.getSubtasks();
            System.out.println("\nКоличество текущих сабтасков: " + subtasks.size());
            for (int i = 0; i < subtasks.size(); i++) {
                System.out.println("Тип: " + subtasks.get(i).getName()
                        + ". Описание: " + subtasks.get(i).getDescription()
                        + ". Статус: " + subtasks.get(i).getStatus()
                        + ". ID: " + subtasks.get(i).getId()
                        + ". Epic ID: " + subtasks.get(i).getEpicId());
            }
            int subtaskId = 7;
            System.out.print("Получение сабтаска по ID = " + subtaskId);
            System.out.println(": ID = " + taskManager.getSubtaskById(subtaskId).getId()
                    + ". Тип: " + taskManager.getSubtaskById(subtaskId).getName()
                    + ". Описание: " + taskManager.getSubtaskById(subtaskId).getDescription()
                    + ". Статус: " + taskManager.getSubtaskById(subtaskId).getStatus()
                    + ". Epic ID: " + taskManager.getSubtaskById(subtaskId).getEpicId());
            System.out.print("Обновление сабтаска по ID = " + subtaskId);
            taskManager.updateSubtask(new Subtask("new name", "new description", "IN_PROGRESS"), subtaskId);
            System.out.println(": ID = " + taskManager.getSubtaskById(subtaskId).getId()
                    + ". Тип: " + taskManager.getSubtaskById(subtaskId).getName()
                    + ". Описание: " + taskManager.getSubtaskById(subtaskId).getDescription()
                    + ". Статус: " + taskManager.getSubtaskById(subtaskId).getStatus()
                    + ". Epic ID: " + taskManager.getSubtaskById(subtaskId).getEpicId());
            taskManager.deleteSubtaskById(subtaskId);
            System.out.println("Список сабтасков после удаления одного по ID: " + +taskManager.getSubtasks().size());
            taskManager.deleteSubtasks();
            if (taskManager.subtasks.isEmpty()) {
                System.out.println("Список сабтасков после удаления всех: " + taskManager.getSubtasks().size());
            }
        }

        if (taskManager.epics.isEmpty()) {
            System.out.println("Список эпиков пуст. Добавьте хотя бы один эпик!");
        } else {
            ArrayList<Epic> epic = taskManager.getEpics();
            System.out.println("\nКоличество текущих эпиков: " + epic.size());
            for (int i = 0; i < epic.size(); i++) {
                System.out.println("Тип: " + epic.get(i).getName()
                        + ". Описание: " + epic.get(i).getDescription()
                        + ". Статус: " + epic.get(i).getStatus()
                        + ". ID: " + epic.get(i).getId());
            }
            int epicId = 6;
            System.out.println("Получение эпика по ID = " + epicId
                    + ". Тип: " + taskManager.getEpicById(epicId).getName()
                    + ". Описание: " + taskManager.getEpicById(epicId).getDescription()
                    + ". Статус: " + taskManager.getEpicById(epicId).getStatus()
                    + ". ID = " + taskManager.getEpicById(epicId).getId());
            System.out.print("Обновление эпика по ID = " + epicId);
            taskManager.updateEpic(new Epic("new epic", "new description", epicId));
            System.out.println(": ID = " + taskManager.getEpicById(epicId).getId()
                    + ". Тип: " + taskManager.getEpicById(epicId).getName()
                    + ". Описание: " + taskManager.getEpicById(epicId).getDescription()
                    + ". Статус: " + taskManager.getEpicById(epicId).getStatus());
            int epicIdForSubtask = 4;
            System.out.println("Количество подзадач у эпика = " + epicIdForSubtask
                    + ": " + taskManager.getSubtasksFromEpicById(epicIdForSubtask).size());
            taskManager.deleteEpicById(epicId);
            System.out.println("Количество эпиков после удаления одного по ID = " + taskManager.getEpics().size());
            taskManager.deleteEpics();
            if (taskManager.epics.isEmpty()) {
                System.out.println("Список эпиков после удаления всех: " + taskManager.getEpics().size());
                System.out.println("Список сабтасков после удаления эпиков: " + taskManager.getSubtasks().size());
            }
        }

    }
}