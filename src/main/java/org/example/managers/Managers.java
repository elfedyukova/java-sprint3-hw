package org.example.managers;

import org.example.managers.file.FileBackedTasksManager;

import java.io.File;

public final class Managers {

    private Managers() {
    }

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getHistoryDefault() {
        return new InMemoryHistoryManager();
    }

    public static TaskManager getFileDefault() {
        return new FileBackedTasksManager(new File("resources/savedata.csv"));
    }

}
