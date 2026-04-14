public class Queue<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> front;
    private Node<T> rear;
    private int size = 0;

    public void display() {
        Node<T> current = front;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);

        if (rear != null) {
            rear.next = newNode;
        }

        rear = newNode;

        if (front == null) {
            front = newNode;
        }

        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        T value = front.data;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;
        return value;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }
}