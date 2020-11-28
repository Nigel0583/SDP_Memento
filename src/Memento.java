import java.awt.image.BufferedImage;

public class Memento {
    private final BufferedImage state;

    public Memento(BufferedImage state) {
        this.state = state;
    }

    public BufferedImage getState() {
        return state;
    }

}
