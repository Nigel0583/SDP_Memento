import java.awt.image.BufferedImage;

import static javax.swing.JOptionPane.showMessageDialog;

public class Originator {
    private final Caretaker caretaker;
    private BufferedImage image;

    public Originator() {
        this.caretaker = new Caretaker();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    private Memento createMemento() {
        return new Memento(image);
    }

    private void restoreMemento(Memento memento) {
        if (memento != null) {
            this.setImage(memento.getState());
        } else {
            showMessageDialog(null, "No state to return to.");
        }
    }

    public void setAndStoreState(BufferedImage newImage) {
        this.setImage(newImage);
        caretaker.addMemento(this.createMemento());
    }

    public void undo() {
        this.restoreMemento(caretaker.undo());
    }

    public void redo() {
        this.restoreMemento(caretaker.redo());
    }
}
