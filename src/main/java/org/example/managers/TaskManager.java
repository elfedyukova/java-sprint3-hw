package org.example.managers;

import org.example.task.Epic;
import org.example.task.Subtask;
import org.example.task.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    public HashMap<Integer, Task> tasks = new HashMap<>();
    public HashMap<Integer, Epic> epics = new HashMap<>();
    public HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private static int id = 1;

    private Integer generateId() {
        return id++;
    }

    public Task createTask(Task task) {
        Integer id = generateId();
        task.setId(id);
        tasks.put(id, task);
        return task;
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task getTaskById(int id) {
        Task task = tasks.get(id);
        return task;
    }

    public void updateTask(Task task) {
        if (task.getId() == tasks.get(task.getId()).getId()) {
            Task oldTask = tasks.get(task.getId());
            oldTask.setStatus(task.getStatus());
            oldTask.setDescription(task.getDescription());
            oldTask.setName(task.getName());
        }
    }

    public void deleteTasks() {
        tasks.clear();
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public Epic createEpic(Epic epic) {
        Integer id = generateId();
        epic.setId(id);
        epics.put(id, epic);
        return epic;
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public Epic getEpicById(int id) {
        Epic epic = epics.get(id);
        return epic;
    }

    public void updateEpic(Epic epic) {
        if (epic.getId() == epics.get(epic.getId()).getId()) {
            Epic oldEpic = epics.get(epic.getId());
            oldEpic.setStatus(epic.getStatus());
            oldEpic.setDescription(epic.getDescription());
            oldEpic.setName(epic.getName());
        }
    }

    public void deleteEpicById(int epicId) {
        //удаляем эпик и сабтаски тоже надо удалить
        ArrayList<Subtask> subtasks = epics.get(epicId).getSubtasks();
        epics.remove(epicId);
        subtasks.clear();
    }

    public void deleteEpics() {
        epics.clear();
        subtasks.clear();
    }

    public ArrayList<Subtask> getSubtasksFromEpicById(int epicId) {
        Epic epic = epics.get(epicId);
        return epic.getSubtasks();
    }

    public void updateEpicStatus(Epic epic) {
        ArrayList<Subtask> subtasks = epic.getSubtasks();
        if (subtasks.isEmpty()) {
            epic.setStatus("NEW");
        }
        boolean isAllNew = true;
        boolean isAllDone = true;

        for (Subtask subtask : subtasks) {
            if (!"NEW".equals(subtask.getStatus())) {
                isAllNew = false;
            }
            if (!"Done".equals(subtask.getStatus())) {
                isAllDone = false;
            }
        }
        if (isAllNew) {
            epic.setStatus("NEW");
        } else if (isAllDone) {
            epic.setStatus("DONE");
        } else {
            epic.setStatus("IN_PROGRESS");
        }
    }

    public Subtask createSubtask(Subtask subtask, int epicsId) {
        if (!epics.containsKey(epicsId)) {
            return null;
        }
        Integer id = generateId();
        subtask.setId(id);
        subtask.setEpicId(epicsId);
        subtasks.put(id, subtask);
        epics.get(epicsId).getSubtasks().add(subtask);

        updateEpicStatus(epics.get(epicsId));
        return subtask;
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public Subtask getSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);
        return subtask;
    }

    public void updateSubtask(Subtask subtask, int subtaskId) {
        subtask.setId(subtaskId);
        subtask.setEpicId(subtasks.get(subtaskId).getEpicId());
        subtasks.put(subtasks.get(subtaskId).getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        updateEpicStatus(epic);
    }

    public void deleteSubtasks() {
        subtasks.clear();  //Если мы все subtasks удаляем эпики могут у нас быть? все эпики, судя по ТЗ должны быть со статусом NEW
        for (Epic epic: epics.values()){
            updateEpicStatus(epic);
        }
    }

    public void deleteSubtaskById(int subtaskId) {
        subtasks.remove(subtaskId); //Если удаляем subtusk нужно ее удалить из эпика и обновить статус.
        epics.remove(epics.get(subtaskId));//нужно передать тот эпик, в который входит эта подзадача
        updateEpicStatus(epics.get(subtaskId));//обновляем статус
    }
}