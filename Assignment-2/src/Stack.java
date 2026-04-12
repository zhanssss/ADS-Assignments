import java.util.ArrayList;

public class Stack {
    private ArrayList<String> data = new ArrayList<>();

    public void push(String value) {
        data.add(value);
    }

    public String pop() {
        if (isEmpty()) return null;
        return data.remove(data.size() - 1);
    }

    public String peek() {
        if (isEmpty()) return null;
        return data.get(data.size() - 1);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    public String get(int index) {
        return data.get(index);
    }
}