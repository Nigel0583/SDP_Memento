import java.util.Stack;

public class Caretaker {
    private final Stack<Memento> undoStack;
    private final Stack<Memento> redoStack;
    private final int max_size = 20;

    public Caretaker() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void addMemento(Memento memento) {
        if (undoStack.size() < max_size) {
            undoStack.push(memento);
        }
        redoStack.clear();
    }

    public Memento undo() {
        if (undoStack.isEmpty()) {
            return null;
        }

        Memento memento = undoStack.pop();
        redoStack.add(memento);
        return memento;
    }

    public Memento redo() {
        if (redoStack.isEmpty()) {
            return null;
        }

        Memento memento = redoStack.pop();
        undoStack.add(memento);
        return memento;
    }
}
