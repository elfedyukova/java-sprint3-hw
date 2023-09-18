package org.example.managers.file;

import org.example.exceptions.ManagerSaveException;
import org.example.managers.InMemoryTaskManager;
import org.example.managers.Managers;
import org.example.managers.TaskManager;
import org.example.task.Epic;
import org.example.task.Subtask;
import org.example.task.Task;

import java.io.*;
import java.nio.file.Files;
import java.util.List;


public class FileBackedTasksManager extends InMemoryTaskManager {

    private File file;
    private static CSVFormatHandler handler = new CSVFormatHandler();

    public FileBackedTasksManager(File file) {
        super();
        this.file = file;
    }

    public static void main(String[] args) throws IOException {

        TaskManager taskManager = Managers.getFileDefault();
        FileBackedTasksManager fileBackedTaskManager = FileBackedTasksManager.loadFromFile(new File("resources/savedata.csv"));

        for (int i = 0; i < 3; i++) {
            taskManager.createTask(new Task("task" + i, "Создание задачи"));
        }
        for (int i = 0; i < 3; i++) {
            taskManager.createEpic(new Epic("epic" + i, "Создание эпика"));
        }
        for (int i = 0; i < 3; i++) {
            int epicId = 4;
            taskManager.createSubtask(new Subtask("subtask" + i, "Это subtask", epicId));
        }

        taskManager.createSubtask(new Subtask("subtask 2 ", "Второй subtask", 6));

        taskManager.getTaskById(2);
        taskManager.getEpicById(5);
        taskManager.getSubtaskById(8);

        System.out.println(fileBackedTaskManager.getHistory());
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
            throw new ManagerSaveException("Can't find file");
        } catch (IOException exception) {
            throw new ManagerSaveException("Can't save to file");
        }
    }

    public static FileBackedTasksManager loadFromFile(File file) throws IOException {

        final FileBackedTasksManager manager = new FileBackedTasksManager(file);
        List<String> content = Files.readAllLines(file.toPath());

        for (int i = 1; i < content.size(); i++) {

            if ("".equals(content.get(i))) {
                break;
            }

            Task task = handler.fromString(content.get(i));

            switch (task.getType()) {
                case TASK:
                    manager.createTask(task);
                    manager.tasks.put(task.getId(), task);
                    break;
                case EPIC:
                    manager.createEpic((Epic) task);
                    break;
                case SUBTASK:
                    manager.createSubtask((Subtask) task);
                    break;
            }
        }
        return manager;
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
    public Subtask createSubtask(Subtask subtask) {
        super.createSubtask(subtask);
        save();
        return subtask;
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

}