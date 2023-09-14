package org.example.managers.file;

import org.example.exceptions.ManagerSaveException;
import org.example.managers.InMemoryTaskManager;
import org.example.managers.Managers;
import org.example.managers.TaskManager;
import org.example.task.Epic;
import org.example.task.Subtask;
import org.example.task.Task;

import java.io.*;
import java.util.List;


public class FileBackedTasksManager extends InMemoryTaskManager {

    private final String path;
    private File file;
    private static CSVFormatHandler handler = new CSVFormatHandler();

    public FileBackedTasksManager(String path) {
        super();
        this.path = path;
        this.file = new File(path);
    }


    public static void main(String[] args) {

        TaskManager taskManager = Managers.getFileDefault();

        for (int i = 0; i < 3; i++) {
            taskManager.createTask(new Task("task", "Создание задачи"));
        }
        for (int i = 0; i < 3; i++) {
            taskManager.createEpic(new Epic("epic", "Создание эпика"));
        }

        for (int i = 0; i < 3; i++) {
            int epicId = 4;
            taskManager.createSubtask(new Subtask("subtask", "Это subtask", epicId));
        }

        taskManager.createSubtask(new Subtask("subtask", "Второй subtask", 6));

        taskManager.getTaskById(2);
        taskManager.getEpicById(5);
        taskManager.getSubtaskById(8);

    }

    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            writer.write(handler.getHeader());
            writer.newLine();

            for (Task task : tasks.values()) {
                writer.write(handler.toString(task));
                writer.newLine();
            }

            for (Epic epic : epics.values()) {
                writer.write(handler.toString(epic));
                writer.newLine();
            }

            for (Subtask subtask : subtasks.values()) {
                writer.write(handler.toString(subtask));
                writer.newLine();
            }

            writer.newLine();
            writer.write(handler.historyToString(historyManager));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            throw new ManagerSaveException("Can't save to file");
        }
    }
    //есть вопрос если создадим 10 тасок и каждую вторую удалим, то как надо сериализовать, чтобы и счётчик тоже изменился??
    static FileBackedTasksManager loadFromFile(File file) {
        FileBackedTasksManager manager = new FileBackedTasksManager(file.getPath());

        String historyRow = "";
        List<Task> history = handler.historyFromString(historyRow);
        for (Task task : history) {
            manager.historyManager.add(task);
        }

        String row = "";
        Task task = handler.fromString(row);
        manager.tasks.put(task.getId(), task);
        return null;
    }

    @Override
    public Task createTask(Task task) {
        super.createTask(task);
        save();
        return task;
    }

    @Override
    public Task getTaskById(int id) {
        Task task = super.getTaskById(id);
        save();
        return task;
    }

    @Override
    public List<Task> getTasks() {
        return super.getTasks();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void deleteTasks() {
        super.deleteTasks();
        save();
    }

    @Override
    public void deleteTaskById(int id) {
        super.deleteTaskById(id);
        save();
    }

    @Override
    public Epic createEpic(Epic epic) {
        super.createEpic(epic);
        save();
        return epic;
    }

    @Override
    public List<Epic> getEpics() {
        return null;
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = super.getEpicById(id);
        save();
        return epic;
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void deleteEpicById(int epicId) {
        super.deleteEpicById(epicId);
        save();
    }

    @Override
    public void deleteEpics() {
        super.deleteEpics();
        save();
    }

    @Override
    public List<Subtask> getSubtasksFromEpicById(int epicId) {
        return null;
    }

    @Override
    public Subtask createSubtask(Subtask subtask) {
        super.createSubtask(subtask);
        save();
        return subtask;
    }

    @Override
    public List<Subtask> getSubtasks() {
        super.getSubtasks();
        save();
        return null;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = super.getSubtaskById(id);
        save();
        return subtask;
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public void deleteSubtasks() {
        super.deleteSubtasks();
        save();
    }

    @Override
    public void deleteSubtaskById(int subtaskId) {
        super.deleteSubtaskById(subtaskId);
        save();
    }

    @Override
    public List<Task> getHistory() {
        return super.getHistory();
    }

}