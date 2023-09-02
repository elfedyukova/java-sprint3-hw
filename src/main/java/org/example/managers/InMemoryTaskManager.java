package org.example.managers;

import org.example.task.Epic;
import org.example.task.Subtask;
import org.example.task.Task;
import org.example.task.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    public HashMap<Integer, Task> tasks = new HashMap<>();
    public HashMap<Integer, Epic> epics = new HashMap<>();
    public HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private static int id = 1;

    private final HistoryManager historyManager = Managers.getHistoryDefault();

    @Override
    public Task createTask(Task task) {
        Integer id = generateId();
        task.setId(id);
        tasks.put(id, task);
        return task;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Task getTaskById(int id) {
        Task task = tasks.get(id);
        historyManager.add(task);
        return task;
    }

    @Override
    public void updateTask(Task task) {
        if (task.getId() == tasks.get(task.getId()).getId()) {
            Task oldTask = tasks.get(task.getId());
            oldTask.setStatus(task.getStatus());
            oldTask.setDescription(task.getDescription());
            oldTask.setName(task.getName());
        }
    }

    @Override
    public void deleteTasks() {
        for (Integer taskId : tasks.keySet()) {
            historyManager.remove(taskId);
        }
        tasks.clear();
    }

    @Override
    public void deleteTaskById(int id) {
        historyManager.remove(id);
        tasks.remove(id);
    }

    @Override
    public Epic createEpic(Epic epic) {
        Integer id = generateId();
        epic.setId(id);
        epics.put(id, epic);
        return epic;
    }

    @Override
    public List<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = epics.get(id);
        historyManager.add(epic);
        return epic;
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epic.getId() == epics.get(epic.getId()).getId()) {
            Epic oldEpic = epics.get(epic.getId());
            oldEpic.setStatus(epic.getStatus());
            oldEpic.setDescription(epic.getDescription());
            oldEpic.setName(epic.getName());
        }
    }

    @Override
    public void deleteEpicById(int epicId) {
        ArrayList<Subtask> subtasks = epics.get(epicId).getSubtasks();
        epics.remove(epicId);
        historyManager.remove(epicId);
        for (Subtask subtask : subtasks) {
            historyManager.remove(subtask.getId());
        }
        subtasks.clear();
    }

    @Override
    public void deleteEpics() {
        for (Integer epicId : epics.keySet()) {
            historyManager.remove(epicId);
        }
        epics.clear();
        for (Integer subtaskId : subtasks.keySet()) {
            historyManager.remove(subtaskId);
        }
        subtasks.clear();
    }

    @Override
    public List<Subtask> getSubtasksFromEpicById(int epicId) {
        Epic epic = epics.get(epicId);
        return epic.getSubtasks();
    }

    @Override
    public Subtask createSubtask(Subtask subtask) {
        if (!epics.containsKey(subtask.getEpicId())) {
            return null;
        }
        Integer id = generateId();
        subtask.setId(id);
        subtask.setEpicId(subtask.getEpicId());
        subtasks.put(id, subtask);
        epics.get(subtask.getEpicId()).getSubtasks().add(subtask);

        updateEpicStatus(epics.get(subtask.getEpicId()));
        return subtask;
    }

    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);
        historyManager.add(subtask);
        return subtask;
    }

    @Override
    public void updateSubtask(Subtask subtask, int subtaskId) {
        subtask.setId(subtaskId);
        subtask.setEpicId(subtasks.get(subtaskId).getEpicId());
        subtasks.put(subtasks.get(subtaskId).getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        updateEpicStatus(epic);
    }

    @Override
    public void deleteSubtasks() {
        for (Integer subtaskId : subtasks.keySet()) {
            historyManager.remove(subtaskId);
        }
        subtasks.clear();
        for (Epic epic : epics.values()) {
            updateEpicStatus(epic);
        }
    }

    @Override
    public void deleteSubtaskById(int subtaskId) {
        Subtask subtask = subtasks.get(subtaskId);
        subtasks.remove(subtaskId);
        epics.remove(epics.get(subtaskId));
        historyManager.remove(subtaskId);
        updateEpicStatus(epics.get(subtask.getEpicId()));
    }

    @Override
    public ArrayList<Task> getHistory() {
        return (ArrayList<Task>) historyManager.getHistory();
    }

    private Integer generateId() {
        return id++;
    }

    private void updateEpicStatus(Epic epic) {
        List<Subtask> subtasks = epic.getSubtasks();
        if (subtasks.isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
        }
        boolean isAllNew = true;
        boolean isAllDone = true;

        for (Subtask subtask : subtasks) {
            if (!"NEW".equals(subtask.getStatus())) {
                isAllNew = false;
            }
            if (!"DONE".equals(subtask.getStatus())) {
                isAllDone = false;
            }
        }
        if (isAllNew) {
            epic.setStatus(TaskStatus.NEW);
        } else if (isAllDone) {
            epic.setStatus(TaskStatus.DONE);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }
}