package util;

import java.util.ArrayList;
import java.util.List;

public class ActivityStack {
    private List<String> stack = new ArrayList<>();

    public void push(String activity) {
        stack.add(activity);
    }

    public String pop() {
        if (!stack.isEmpty()) {
            return stack.remove(stack.size() - 1);
        }
        return null;
    }

    public List<String> getAllActivities() {
        return new ArrayList<>(stack);
    }
}