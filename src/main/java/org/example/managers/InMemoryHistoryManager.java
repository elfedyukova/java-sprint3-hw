package org.example.managers;

import org.example.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    CustomLinkedList<Task> historyTasks = new CustomLinkedList<>();
    Map<Integer, Node> historyMap = new HashMap<>();

    @Override
    public List<Task> getHistory() {
        return historyTasks.getTasks();
    }

    @Override
    public void remove(int id) {
        if (!historyMap.containsKey(id)) {
            return;
        }
        Node remove = historyMap.get(id);
        historyTasks.removeNode(remove);
        historyMap.remove(id);
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        if (historyMap.containsKey(task.getId())) {
            Node remove = historyMap.get(task.getId());
            historyTasks.removeNode(remove);
        }
        historyTasks.linkLast(task);
        historyMap.put(task.getId(), historyTasks.tail);
    }

    private static class CustomLinkedList<T> {

        private Node<T> head;
        private Node<T> tail;

        public List<Task> getTasks() {
            List<Task> history = new ArrayList<>();
            Node<T> node = head;
            while (node != null) {
                history.add((Task) node.task);
                node = node.next;
            }
            return history;
        }

        public void linkLast(T task) {
            final Node<T> prev = tail;
            final Node<T> node = new Node<>(prev, task, null);
            tail = node;

            if (prev != null) {
                prev.next = node;

            } else {
                head = node;
            }
        }

        public void removeNode(Node<T> node) {

            if (node == null) {
                return;
            }
            if (node.prev != null) {
                node.prev.next = node.next;
                if (node.next == null) {
                    tail = node.prev;
                } else {
                    node.next.prev = node.prev;
                }
            } else {
                head = node.next;
                if (head == null) {
                    tail = null;
                } else {
                    head.prev = null;
                }
            }
        }

    }

}
