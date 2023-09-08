package org.example.managers;

import org.example.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    private CustomLinkedList<Task> historyTasks = new CustomLinkedList<>();
    private Map<Integer, Node<Task>> historyMap = new HashMap<>();

    @Override
    public List<Task> getHistory() {
        return historyTasks.getTasks();
    }

    @Override
    public void remove(int id) {
        if (!historyMap.containsKey(id)) {
            return;
        }
        Node<Task> remove = historyMap.get(id);
        historyTasks.removeNode(remove);
        historyMap.remove(id);
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        if (historyMap.containsKey(task.getId())) {
            Node<Task> remove = historyMap.get(task.getId());
            historyTasks.removeNode(remove);
        }
        historyTasks.linkLast(task);
        historyMap.put(task.getId(), historyTasks.tail);
    }

    private static class CustomLinkedList<T> {

        private Node<T> head;
        private Node<T> tail;

        public List<T> getTasks() {
            List<T> history = new ArrayList<>();
            Node<T> node = head;
            while (node != null) {
                history.add(node.task);
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

            final Node<T> prev = node.prev;
            final Node<T> next = node.next;


            if (prev != null) {
                prev.next = next;
                if (next == null) {
                    tail = prev;
                } else {
                    next.prev = prev;
                }
            } else {
                head = next;
                if (head == null) {
                    tail = null;
                } else {
                    head.prev = null;
                }
            }
        }

    }

    private static class Node<T> {

        private T task;
        private Node<T> next;
        private Node<T> prev;

        public Node(Node<T> prev, T task, Node<T> next) {
            this.task = task;
            this.next = next;
            this.prev = prev;
        }
    }

}
