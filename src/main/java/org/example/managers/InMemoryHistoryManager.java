package org.example.managers;

import org.example.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    CustomLinkedList<Task> historyTasks = new CustomLinkedList<>();
    Map<Integer, Node> map = new HashMap<>();
    Node first;
    Node last;

    @Override
    public List<Task> getHistory() {
        return historyTasks.getTasks();
    }

    @Override
    public void remove(int id) {
        Node<Task> node = null;
        historyTasks.removeNode(node);
    }

    @Override
    public void addTask(Task task) {
        if (task == null) {
            return;
        }
        historyTasks.linkLast(task);
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
