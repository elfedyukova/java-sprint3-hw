package org.example.managers;

import org.example.task.Task;
import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    //private final LinkedList<Task> history = new LinkedList<>();
    CustomLinkedList<Task> historyTasks = new CustomLinkedList<>();

    Node first;
    Node last;

    Map<Integer, Node> map = new HashMap<>();

    @Override
    public List<Task> getHistory() {
        List<Task> result = new ArrayList<>();
        if (first == null) {
            return  result;
        }
        Node current = first;

        while (current != null){
            //result.add(current.getValue());
            current = current.next;
        }
        return result;
    }

    @Override
    public void remove(int id){
         // для удаления задачи из просмотра
        // Добавьте его вызов при удалении задач, чтобы они также удалялись из истории просмотров.
        //Node<Task> node;
        //historyTasks.removeNode(node);
    }

    @Override
    public void addTask(Task task) {


    }

    private static class CustomLinkedList<T> {

        private Node<T> head;
        private Node<T> tail;

        //getTasks собирать все задачи из него в обычный ArrayList.
        public List<Task> getTasks () {
            List<Task> history = new ArrayList<>();
            Node<T> node = head;
            while (node != null) {
                history.add((Task) node.task);
                node = node.next;
            }
            return history;
        }

        //будет добавлять задачу в конец этого списка
        public void linkLast(T task) {

            final Node<T> prev = tail;
            final Node<T> node = new Node<>(prev, task, null);
            tail = node;

            if (prev != null) {
                prev.next = node;

            }else{
                head = node;
            }

        }
        // качестве параметра этот метод должен принимать объект  Node — узел связного списка и вырезать его
        public void removeNode(Node<T> node) {
            if (node == null) {
                return;
            }
            if (node.prev != null) {
                node.prev.next = node.next;
                if (node.next == null) { // node == next
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
