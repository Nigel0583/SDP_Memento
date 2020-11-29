import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestGraphic {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throwsIOException() throws Exception {
        exception.expect(IOException.class);
        exception.expectMessage("An error occurred loading the file!");
        DrawGraphic drawGraphic = new DrawGraphic();
        drawGraphic.load("test.png");
        throw new IOException("An error occurred loading the file!");
    }

}
