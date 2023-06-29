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

    public Epic createEpic(Epic epic) {
        Integer id = generateId();
        epic.setId(id);
        epics.put(id, epic);
        return epic;
    }

    public void createSubtask(Subtask subtask, int epicsId) { //Ты создаешь subtask ее же надо в эпик положить и статус обновить.
        Integer id = generateId();
        subtask.setId(id);
        subtasks.put(id, subtask);
        subtask.setEpicId(epicsId);
        System.out.println("createSubtask"+ subtask.getEpicId() + subtask.getId());
        if (epics.containsKey(subtask.getEpicId())) { //если эпик содержит ключ 4 положи сабтаск в эпик
           // epics.put(epics.get(subtask.getEpicId()), );
            System.out.println( epics.get(subtask.getEpicId()).getSubTasks());  //put(id, subtask);
        }
        //checkEpicStatus(subtask.getIdEpic());
    }

    //Ты создаешь subtask ее же надо в эпик положить и статус обновить.
    public void createSubtasks(Subtask subtask) {
        Integer id = generateId();
        subtask.setId(id);
        //subtasks.put(id, subtask);
        subtask.setEpicId(subtask.getEpicId());

        if (epics.containsKey(subtask.getEpicId())) {
            System.out.println(epics.get(subtask.getEpicId()).getId()); //id epica
            subtasks.put(epics.get(subtask.getEpicId()).getId(), subtask);
            System.out.println("EpicId" + subtask.getEpicId());
         }
        //checkEpicStatus(subtask.getIdEpic());;

    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public Task getTaskById(int id) {
        Task task = tasks.get(id);
        return task;
    }

    public Epic getEpicById(int id) {
        Epic epic = epics.get(id);
        return epic;
    }

    public Subtask getSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);
        return subtask;
    }

    public Subtask getSubtasksFromEpicById(int epicId) {
        System.out.println("что пришло в метод" + epicId);
        Epic epic = epics.get(epicId);
        System.out.println("what epic" + epic);
        Subtask subtask = subtasks.get(epicId);
        //int epic = subtask.getEpicId();
        //int epic = epics.get(epicIdForSubtask).getId();
        System.out.println(subtask);
        //epics.get(epicIdForSubtask).getId();
        return subtask; //???
    }

    public String getsub (int epicId){
        String subTaskList = "";

        Epic epic = (Epic) tasks.get(epicId);
        //if (epic.getSubTasks().size() != 0) {
           // for (Integer taskID : epic.getSubTasks().size()) {
            //    subTaskList += "\n" + epic.getSubTasks().get(taskID);

            System.out.println("hghghfh" + epic);
            return subTaskList;
        //} else {
         //   return "Эпик пуст";
       // }

    }

    public void deleteTasks() {
        tasks.clear();
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void deleteSubtasks() {
        subtasks.clear();
    }

    public void deleteSubtaskById(int subtaskId) {
        subtasks.remove(subtaskId);
    }

    public void deleteEpics() {
        epics.clear();
    }

    public void deleteEpicById(int epicId) {
        epics.remove(epicId);
    }

    public void updateTask(Task task) {
        if(task.getId() == tasks.get(task.getId()).getId()){
            Task oldTask = tasks.get(task.getId());
            oldTask.setStatus(task.getStatus());
            oldTask.setDescription(task.getDescription());
            oldTask.setName(task.getName());
        }
    }
    public void updateEpic(Epic epic) {
        if(epic.getId() == epics.get(epic.getId()).getId()){
            Epic oldEpic = epics.get(epic.getId());
            oldEpic.setStatus(epic.getStatus());
            oldEpic.setDescription(epic.getDescription());
            oldEpic.setName(epic.getName());
        }
    }

    public void updatesb(Subtask subtask) {
        if(subtask.getId() == subtasks.get(subtask.getId()).getId()){
            Subtask oldSubtask = subtasks.get(subtask.getId());
            oldSubtask.setStatus(subtask.getStatus());
            oldSubtask.setDescription(subtask.getDescription());
            oldSubtask.setName(subtask.getName());
        }
    }

    public void updateSubtask(Subtask subtask, int subtaskId) {
        subtask.setId(subtaskId);
        subtask.setEpicId(subtasks.get(subtaskId).getEpicId());
        subtasks.put(subtasks.get(subtaskId).getId(), subtask);
        // checkEpicStatus(subtasks.get(subtaskId).getIdEpic()); //?? При обновлении subtask нужно обновить и статус у эпика так как он зависит от статусов подзадач
    }

    public void checkEpicStatus() {
        for (Epic epic : epics.values()) {
            if (epic.getStatus() == "NEW") {
                epic.setStatus("NEW");//если у эпика нет подзадач или все они имеют статус NEW, то статус должен быть NEW
                // epic.setStatus("DONE");
                // epic.setStatus("IN_PROGRESS");
            }

        }

    }
}
//У тебя тут алгоритм не правильный. Ты же не знаешь как ие у тебя статусы в подзадачах при изменении.
// Если меняешь или добавляешь задачу нужно проверить все статусы пордзадач и установить статус эпика в зависимости от их статусов.