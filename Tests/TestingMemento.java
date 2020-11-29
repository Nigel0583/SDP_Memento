import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

public class TestingMemento {
    BufferedImage originalImage =
            ImageIO.read(new File("C:/Users/nigel/OneDrive/Documents/test.png"));
    BufferedImage testImage =
            ImageIO.read(new File("C:/Users/nigel/OneDrive/Documents/testImage2.png"));
    /*
    Learned about byteArray here:
    https://stackoverflow.com/questions/31279009/testing-image-files-with-junit
    */

    byte[] originalImageData = ((DataBufferByte) originalImage.getData().getDataBuffer()).getData();
    byte[] testImageData = ((DataBufferByte) testImage.getData().getDataBuffer()).getData();
    Originator originator = new Originator();

    public TestingMemento() throws IOException {
    }

    @Test
    public void testSetAndStoreState() {
        originator.setAndStoreState(originalImage);
        BufferedImage getImg = originator.getImage();
        byte[] actualData = ((DataBufferByte) getImg.getData().getDataBuffer()).getData();
        assertArrayEquals(originalImageData, actualData);
    }

    @Test
    public void testUndoAndRedo() {
        originator.setAndStoreState(originalImage);
        originator.setAndStoreState(testImage);
        originator.undo();
        originator.undo();
        BufferedImage undoImage = originator.getImage();
        byte[] undoData = ((DataBufferByte) undoImage.getData().getDataBuffer()).getData();
        assertArrayEquals(undoData, originalImageData);

        originator.redo();
        originator.redo();
        BufferedImage redoImage = originator.getImage();
        byte[] redoData = ((DataBufferByte) redoImage.getData().getDataBuffer()).getData();
        assertArrayEquals(redoData, testImageData);
    }


}
