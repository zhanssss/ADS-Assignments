public class Stack {
    private static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
        }
    }

    private Node top;
    private int size = 0;

    public void push(String value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }

        String value = top.data;
        top = top.next;
        size--;
        return value;
    }

    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node current = top;
        for (int i = size - 1; i > index; i--) {
            current = current.next;
        }

        return current.data;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }

        Node current = top;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}